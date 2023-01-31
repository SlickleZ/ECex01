import java.net.*; 

class UDPServer { 
  public static void main(String args[]) throws Exception { 
    try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
      /* Define spec of data */
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024];

      while(true) {
        System.out.println("The server is waiting ");

        /* Define spec of received packet */
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        serverSocket.receive(receivePacket); // wait for receive

        /* Extract packet */
        String sentence = new String(receivePacket.getData());
        InetAddress IPAddress = receivePacket.getAddress(); 
        int port = receivePacket.getPort();

        /* Do something with data */
        String capitalizedSentence = sentence.toUpperCase();
        sendData = capitalizedSentence.getBytes(); // Convert to byte to send

        /* Define spec of send packet */
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port); 
        serverSocket.send(sendPacket); // send it!
      }
    }
  }
}