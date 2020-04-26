import grpc

from server.pb_python import cv_pb2_grpc, cv_pb2


def generator():
    req = cv_pb2.Request(
        action=cv_pb2.Request.START,
        country=cv_pb2.Poland)
    yield req
    # req = cv_pb2.Request(
    #     action=cv_pb2.Request.START,
    #     country=cv_pb2.US)
    # sleep(3)
    # yield req
    # sleep(3)
    # resp = cv_pb2.Request(
    #     action=cv_pb2.Request.STOP)
    # yield resp


if __name__ == "__main__":
    print("client")
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = cv_pb2_grpc.CVStub(channel)
        resp = stub.Subscription(generator())

        resp.next()
        for res in resp:
            print(res)
