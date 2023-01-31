import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class DT_UDPServer {
    public static void main(String[] args) throws Exception {
        final int PORT = 1112;
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        try (DatagramSocket udpSocket = new DatagramSocket(PORT)) {
            while (true) {
                System.out.println("Server is waiting for client connection at port number " + PORT);

                DatagramPacket receivedPacket = new DatagramPacket(receiveData, receiveData.length);
                udpSocket.receive(receivedPacket);

                Date now = new Date();
                String dateTimeFormat = now.toString();
                sendData = dateTimeFormat.getBytes();

                InetAddress IPAddress = receivedPacket.getAddress();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, receivedPacket.getPort());
                udpSocket.send(sendPacket);
                System.out.println("Packet is sent from server\n");

                Thread.sleep(1000);
            }
        }
    }
}
