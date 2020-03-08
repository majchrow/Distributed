import logging
import select
import struct
import sys
from socket import *

from art import text2art

from utils import enable_logger


class Client:
    def __init__(self, server_ip='127.0.0.1', server_port=65432, client_ip='127.0.0.1',
                 multicast_ip='224.1.1.1', multicast_port=5007):
        self.server_addr = (server_ip, server_port)
        self.multicast_addr = (multicast_ip, multicast_port)
        self.ip = client_ip
        self.sockets_list = [sys.stdin]
        enable_logger(dir="logs/client", filename="client")

    def start_client(self):
        self._start_tcp_socket()
        self._start_udp_socket()
        self._start_multicast_socket()

        while True:
            try:
                # Select correct reading descriptor
                read_sockets, write_sockets, error_sockets = select.select(self.sockets_list, [], [])
                for sock in read_sockets:
                    if sock == self.tcp_socket:  # Tcp socket
                        data = self.tcp_socket.recv(1024).decode().rstrip()
                        if not data:
                            self._unconnect()
                        else:
                            logging.info(data)
                    elif sock == self.udp_socket:  # Udp socket
                        msg = self.udp_socket.recv(4096).decode()
                        logging.info(msg)
                    elif sock == self.multicast_socket:  # Udp Multicast
                        msg = self.multicast_socket.recv(4096).decode()
                        if self.my_id not in msg:
                            logging.info(msg)
                    else:  # Stdin
                        message = sys.stdin.readline()
                        if message.startswith('U'):
                            message = "\n" + text2art(message[1:])
                            self.udp_socket.sendto(message.encode(), self.server_addr)
                        elif message.startswith('M'):
                            message = "\n" + text2art(message[1:])
                            send_message = f"<{self.my_id}|UDP_MULTICAST>" + message
                            self.multicast_socket.sendto(send_message.encode(), self.multicast_addr)
                        else:
                            self.tcp_socket.send(message.encode())
                        logging.info(f"<You>: {message}")
                    sys.stdout.flush()
            except KeyboardInterrupt:
                self._unconnect()

    def _start_tcp_socket(self):
        self.tcp_socket = socket(AF_INET, SOCK_STREAM)
        self.tcp_socket.connect(self.server_addr)
        self.port = self.tcp_socket.getsockname()[1]  # connected port
        self.sockets_list.append(self.tcp_socket)
        logging.info(f"Connected to the server {self.server_addr} on TCP port {self.port}")
        self._get_id_from_server()

    def _get_id_from_server(self):
        data = self.tcp_socket.recv(1024).decode().rstrip()
        if not data:
            self._unconnect()
        logging.info(data)
        self.my_id = (data.split('<')[2]).split('>')[0]

    def _start_udp_socket(self):
        self.udp_socket = socket(AF_INET, SOCK_DGRAM)
        assert self.port, "Can't open UDP socket, when TCP socket is not UP"
        self.udp_socket.bind((self.ip, self.port))
        self.sockets_list.append(self.udp_socket)
        logging.info(f"Connected to the server {self.server_addr} on UDP port {self.port}")

    def _start_multicast_socket(self):
        self.multicast_socket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)
        self.multicast_socket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
        self.multicast_socket.bind(self.multicast_addr)
        mreq = struct.pack("4sl", inet_aton(self.multicast_addr[0]), INADDR_ANY)
        self.multicast_socket.setsockopt(IPPROTO_IP, IP_ADD_MEMBERSHIP, mreq)
        self.multicast_socket.setsockopt(IPPROTO_IP, IP_MULTICAST_TTL, 2)
        self.sockets_list.append(self.multicast_socket)

    def _unconnect(self):
        for socket in self.sockets_list:
            if socket and socket != sys.stdin:
                socket.close()
        logging.info(f"{getattr(self, 'my_id', 'Client unknown')} disconnecting")
        sys.exit()


if __name__ == '__main__':
    client = Client()
    client.start_client()
