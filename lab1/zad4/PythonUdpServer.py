import socket;

serverPort = 9009
clientPort = 9010
serverIP = 'localhost'
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
serverSocket.bind(('', serverPort))
buff = []
	
print('PYTHON UDP SERVER')

while True:

    buff, address = serverSocket.recvfrom(1024)
    msg = str(buff, 'UTF-8')
    print("recived msg: " + msg)
    if 'Python' in msg:
    	serverSocket.sendto(bytes('Pong Python', 'UTF-8'), (serverIP, clientPort))
    elif 'Java' in msg:
    	serverSocket.sendto(bytes('Pong Java', 'UTF-8'), (serverIP, clientPort))
    else:
    	serverSocket.sendto(bytes('Who are you?', 'UTF-8'), (serverIP, clientPort))

