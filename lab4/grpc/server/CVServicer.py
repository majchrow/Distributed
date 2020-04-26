import logging
import uuid
from concurrent import futures
from time import sleep

import grpc

from ServantFactory import ServantFactory
from pb_python import cv_pb2_grpc, cv_pb2


def get_country_mapping(country: cv_pb2.Country):
    mappings = {cv_pb2.Poland: "pl_PL",
                cv_pb2.France: "fr_FR",
                cv_pb2.Japan: "ja_JP",
                cv_pb2.Germany: "de_DE",
                }
    return mappings.get(country, "en_US")  # en_US is default, proto doesn't serialize enum values=0


class CVServicer(cv_pb2_grpc.CVServicer):

    def __init__(self):
        self.executor = futures.ThreadPoolExecutor(max_workers=5)
        self.servant_factory = ServantFactory()  # Generating data for all utils.EVENTS asynchronously
        self.clients_states = {}

    def handle_input_data(self, request_iterator, context, unique_id):
        for request in request_iterator:
            logging.info(f"Received from stream client {context.peer()} stream `{unique_id}` request `{request}`")
            if request.action == cv_pb2.Request.STOP:
                self.servant_factory.unsubscribe(unique_id)
            else:  # Start is default
                new_country = get_country_mapping(request.country)
                old_country = self.servant_factory.get_stream_country(unique_id)
                if not old_country:  # No Countries subscribed
                    self.servant_factory.subscribe_on(unique_id, new_country)
                elif old_country != new_country:
                    logging.info(f"Wrong request from stream `{unique_id}` client {context.peer()}")
                    context.abort(
                        code=grpc.StatusCode.INVALID_ARGUMENT,
                        details="You can have only one independent subscribed country!"
                    )
        self.servant_factory.unsubscribe(unique_id)
        client_streams = self.clients_states.get(context.peer(), {})
        client_streams[unique_id] = "Disconnected"

    def Subscription(self, request_iterator, context):
        unique_id = uuid.uuid4()

        if context.peer() not in self.clients_states:
            self.clients_states[context.peer()] = {}

        self.clients_states[context.peer()][unique_id] = "Running"

        logging.info(f"New stream `{unique_id}` for client {context.peer()}")
        self.executor.submit(self.handle_input_data, request_iterator, context, unique_id)
        while True:
            client_streams = self.clients_states.get(context.peer(), {})
            stream_info = client_streams.get(unique_id, "")
            if stream_info == "Disconnected":
                del client_streams[unique_id]
                logging.info(f"Finished stream `{unique_id}` connection with client {context.peer()}")
                return
            for response in self.servant_factory.get_new_events(unique_id):
                logging.info(
                    f"Sending to client {context.peer()} stream `{unique_id}`response `***hidden for readability`")
                yield response
            sleep(3)
