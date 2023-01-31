//TCPConcurrentServer.java
import java.io.*; 
import java.net.*;
public class TCPConcurrentServer { 
   public static void main(String argv[])  { 
      ServerSocket welcomeSocket = null;
      try {
         welcomeSocket = new ServerSocket(6789); // init welcome socket
      }
      catch (IOException e) {
         System.out.println("Cannot create a welcome socket");
         System.exit(1);
      }
      while(true) {
         try {
            System.out.println("The server is waiting ");
			   // waiting for client to contact, then create new connection socket
            Socket connectionSocket = welcomeSocket.accept(); 
			   
            // new request, new thread
            EchoThread echoThread = new EchoThread(connectionSocket);
            echoThread.start();
         }
         catch (IOException e) {
            System.out.println("Cannot create this connection");
         } finally {
            try {
               welcomeSocket.close();
            } catch (IOException e) {
               e.printStackTrace();
            } // close the welcome socket after done. (connection socket already closed in thread)
         }
      }
   } 
} 
