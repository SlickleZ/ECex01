import java.io.*; 
import java.net.*;
import java.util.*; 
class TCPClient { 
    public static void main(String argv[]) throws Exception 
    { 
         String sentence; 
         String modifiedSentence;
         Scanner inFromUser = null; // buffer input
         Socket clientSocket = null; // client socket
         DataOutputStream outToServer = null; // bytes stream output to server
         Scanner inFromServer = null; // output from server to client
         try { 
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 6789); // client socket
            outToServer = new DataOutputStream(clientSocket.getOutputStream()); // init output stream of client socket
            inFromServer = new Scanner(clientSocket.getInputStream()); // init input stream of client socket

            // user input
            System.out.print("Please enter words: ");
            sentence = inFromUser.nextLine(); 
            inFromUser.close();

            // send to server
            outToServer.writeBytes(sentence + '\n');
            
            // read from server (Input stream)
            modifiedSentence = inFromServer.nextLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
         }
         catch (IOException e) {
             System.out.println("Error occurred: Closing the connection");
         }
         finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
         } 
    } 
} 