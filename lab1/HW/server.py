import logging
from concurrent.futures import ThreadPoolExecutor as Pool
from queue import Queue
from socket import *

from utils import enable_logger


class Server:
    def __init__(self, ip='127.0.0.1', port=65432):
        self.ip = ip
        self.port = port
        self.MAX_CLIENTS = 3
        self.messages = Queue()  # synchronized message queue
        self.clients = []  # (client, client_id)
        self.addresses = []  # (ip, port)
        self.executor = Pool(max_workers=self.MAX_CLIENTS + 1)
        enable_logger(dir="logs/server", filename="server")

    def start_chat(self):
        self._start_tcp_socket()
        self._start_udp_socket()
        self.executor.submit(self.client_udp_handler)  # Start UDP client
        while True:
            try:
                client, addr = self.tcp_socket.accept()
                if len(self.clients) == self.MAX_CLIENTS:
                    client.close()
                    continue
                clients_ids = [client_id for _, client_id in self.clients]
                client_id = min(set(range(1, len(clients_ids) + 2)) - set(clients_ids))  # find suitable client_id
                logging.info(f"Established connection with {addr} on TCP port. Starting new thread")
                self.executor.submit(self.client_tcp_handler, client, client_id)
                self.addresses.append(addr)
                logging.info(f"Thread for Client {client_id} started")
            except KeyboardInterrupt:
                self._unconnect()

    def _start_tcp_socket(self):
        self.tcp_socket = socket(AF_INET, SOCK_STREAM)
        self.tcp_socket.getsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
        self.tcp_socket.bind((self.ip, self.port))
        self.tcp_socket.listen()
        logging.info(f"Server {self.ip} listening on TCP port {self.port}")

    def _start_udp_socket(self):
        self.udp_socket = socket(AF_INET, SOCK_DGRAM)
        self.udp_socket.bind((self.ip, self.port))
        logging.info(f"Server {self.ip} listening on UDP port {self.port}")

    def client_udp_handler(self):
        while True:
            data, ip = self.udp_socket.recvfrom(4096)
            client_id = [c_id for client, c_id in self.clients if client.getpeername() == ip][0]
            for client_ip in self.addresses:
                if ip != client_ip:
                    message = f"Client <{client_id}|UDP>:\n" + data.decode()
                    self.udp_socket.sendto(message.encode(), client_ip)

    def client_tcp_handler(self, client, client_id):
        self.clients.append((client, client_id))
        existing_clients = sorted([existing_client_id for _, existing_client_id in self.clients])
        message = f"<Server|TCP> Welcome to the chat <Client {client_id}>! Clients online: {existing_clients}"
        client.send(message.encode())
        while True:
            message = client.recv(1024)
            if not message or "exit()" in message.decode().lower():
                break
            message = f"<Client {client_id}|TCP>: {message.decode().rstrip()}"
            message = message.encode()
            self.messages.put((message, client_id))
            self.propagate()
        self.remove_connection(client_id)
        logging.info(f"Thread for Client {client_id} finished")

    def remove_connection(self, client_id):
        for index, (_, terminating_client_id) in enumerate(self.clients):
            if client_id == terminating_client_id:
                client, client_id = self.clients.pop(index)
                if client:
                    client.close()
                logging.info(f"Connection with client {client_id} down")
                return

    def propagate(self):
        message, sender_client_id = self.messages.get()
        for client, receiver_client_id in self.clients:
            if sender_client_id != receiver_client_id:
                client.send(message)

    def _unconnect(self):
        for _, client_id in self.clients:
            self.remove_connection(client_id)
        if self.udp_socket:
            self.udp_socket.close()
        if self.tcp_socket:
            self.tcp_socket.close()
        self.executor.shutdown(wait=False)


if __name__ == '__main__':
    server = Server()
    server.start_chat()
