import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.net.InetAddress;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");
        DatagramSocket socket = null;
        int portNumber = 9008;

        try{
            socket = new DatagramSocket(portNumber);
            byte[] receiveBuffer = new byte[1024];

            while(true) {
                Arrays.fill(receiveBuffer, (byte)0);
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                int nb = ByteBuffer.wrap(receivePacket.getData()).getInt();
                nb = Integer.reverseBytes(nb);
                System.out.println("received msg: " + nb);
                nb = Integer.reverseBytes(nb+1);
                receiveBuffer = ByteBuffer.allocate(4).putInt(nb).array();
                InetAddress address = InetAddress.getByName("localhost");

                DatagramPacket sendPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length, address, 9009);
                socket.send(sendPacket);
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
