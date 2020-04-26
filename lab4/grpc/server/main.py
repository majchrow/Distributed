import logging
from concurrent import futures

import grpc

from CVServicer import CVServicer
from pb_python import cv_pb2_grpc
from utils import enable_logger


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))
    cv_pb2_grpc.add_CVServicer_to_server(
        CVServicer(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    logging.info("Started on port 50051")
    server.wait_for_termination()


if __name__ == "__main__":
    enable_logger(directory="logs/server", filename="server")
    serve()
