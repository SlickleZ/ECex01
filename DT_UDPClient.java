import java.io.IOException;
import java.net.*;

public class DT_UDPClient {
    public static void main(String[] args) {
        final int SERVER_PORT = 1112;
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while(true) {
            try (DatagramSocket clientSocket = new DatagramSocket()) {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                sendData = "Dummy".getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
                // System.out.println("Waiting for receive data");
                clientSocket.receive(receivedPacket);

                String recData = new String(receivedPacket.getData());
                System.out.println("Receive: " + recData + " at " + receivedPacket.getAddress() + ":" + receivedPacket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
