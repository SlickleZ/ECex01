import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class DT_UDPServer {
    public static void main(String[] args) throws Exception {
        final int PORT = 1112;
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            byte[] sendData = new byte[1024];

            System.out.println("Server is sending date to client connection at port number " + PORT);
            while (true) {
                Date now = new Date();
                String dateTimeFormat = now.toString();
                sendData = dateTimeFormat.getBytes();
                
                InetAddress IPAddress = InetAddress.getByName("localhost");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
                udpSocket.send(sendPacket);

                System.out.println(dateTimeFormat);

                Thread.sleep(1000);
            }
        }
    }
}
