import logging
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

    def handle_input_data(self, request_iterator, context):
        for request in request_iterator:
            logging.info(f"Received from {context.peer()} request `{request}`")
            if request.action == cv_pb2.Request.STOP:
                self.servant_factory.unsubscribe(context.peer())
            else:  # Start is default
                new_country = get_country_mapping(request.country)
                old_country = self.servant_factory.get_client_country(context.peer())
                if not old_country:  # No Countries subscribed
                    self.servant_factory.subscribe_on(context.peer(), new_country)
                elif old_country != new_country:
                    logging.info(f"Wrong request from {context.peer()}")
                    context.abort(
                        code=grpc.StatusCode.INVALID_ARGUMENT,
                        details="You can have only one independent subscribed country!"
                    )
        self.servant_factory.unsubscribe(context.peer())
        self.clients_states[context.peer()] = "Disconnected"

    def Subscription(self, request_iterator, context):
        logging.info(f"New servant for client {context.peer()}")
        self.executor.submit(self.handle_input_data, request_iterator, context)
        while True:
            if self.clients_states.get(context.peer(), "") == "Disconnected":
                logging.info(f"Finished stream connection with {context.peer()}")
                return
            for response in self.servant_factory.get_new_events(context.peer()):
                logging.info(f"Sending to {context.peer()} response `***hidden for readability`")
                yield response
            sleep(15)
