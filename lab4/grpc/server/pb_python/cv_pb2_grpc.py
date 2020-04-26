# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
import grpc

import pb_python.cv_pb2 as cv__pb2


class CVStub(object):
    """Missing associated documentation comment in .proto file"""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.Subscription = channel.stream_stream(
                '/CV/Subscription',
                request_serializer=cv__pb2.Request.SerializeToString,
                response_deserializer=cv__pb2.CurriculumVitae.FromString,
                )


class CVServicer(object):
    """Missing associated documentation comment in .proto file"""

    def Subscription(self, request_iterator, context):
        """Missing associated documentation comment in .proto file"""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_CVServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'Subscription': grpc.stream_stream_rpc_method_handler(
                    servicer.Subscription,
                    request_deserializer=cv__pb2.Request.FromString,
                    response_serializer=cv__pb2.CurriculumVitae.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'CV', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class CV(object):
    """Missing associated documentation comment in .proto file"""

    @staticmethod
    def Subscription(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(request_iterator, target, '/CV/Subscription',
            cv__pb2.Request.SerializeToString,
            cv__pb2.CurriculumVitae.FromString,
            options, channel_credentials,
            call_credentials, compression, wait_for_ready, timeout, metadata)