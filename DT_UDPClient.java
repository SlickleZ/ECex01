import java.io.IOException;
import java.net.*;

public class DT_UDPClient {
    public static void main(String[] args) {
        final int PORT = 1112;
        byte[] receiveData = new byte[1024];

        while(true) {
            try {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                DatagramSocket clientSocket = new DatagramSocket(PORT);

                DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, PORT);
                // System.out.println("Waiting for receive data");
                clientSocket.receive(receivedPacket);

                String recData = new String(receivedPacket.getData());
                System.out.println("Receive: " + recData + " at " + receivedPacket.getAddress() + ":" + receivedPacket.getPort());
                
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
