import java.net.*;
import java.util.*; 
class UDPClient { 
   public static void main(String args[]) throws Exception { 
      Scanner inFromUser = new Scanner(System.in); // init user buffer

      DatagramSocket clientSocket = new DatagramSocket(); // init socket
      InetAddress IPAddress = InetAddress.getByName("localhost"); // get ip 
      
      /* Define spec of data */
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];


      System.out.print("Please enter words: ");
      String sentence = inFromUser.nextLine(); // get user input
      inFromUser.close();

      sendData = sentence.getBytes(); // convert to byte to send

      /* Define spec of send packet and send it! */
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);

      /* Define spec of received packet and block to receive it */
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);

      /* Extract data from received packet */
      String modifiedSentence = new String(receivePacket.getData()); 
      System.out.println("FROM SERVER:" + modifiedSentence.trim()); 
	   
      /* Clear socket connection */
      clientSocket.close();
   } 
} 
