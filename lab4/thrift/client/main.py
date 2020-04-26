from thrift.protocol import TBinaryProtocol
from thrift.protocol.TMultiplexedProtocol import TMultiplexedProtocol
from thrift.transport import TSocket, TTransport

from thrift_python.smarthome import SmartHome
from handlers import SmartHomeClientHandler
from utils import enable_logger
import logging
import time

if __name__ == '__main__':
    enable_logger(filename="client")
    transport = TSocket.TSocket('localhost', 9090)
    transport = TTransport.TBufferedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport, True, True)
    client = SmartHome.Client(TMultiplexedProtocol(protocol, "smart_home"))
    transport.open()
    devices = client.getDevicesIds()
    smart_home = SmartHomeClientHandler(devices, protocol)
    logging.info("Connected")
    time.sleep(0.1)
    smart_home.start()
