import java.io.*;
import java.net.*;

public class CalculatorTCPServer {
        public static void main(String[] args) {
            ServerSocket welcomeSocket = null;
            int port = 1112;

            try {
                welcomeSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.out.println("Welcome socket cannot be init.\nError: " + e.getMessage());
                System.exit(1);
            }

            while(true) {
                try {
                    System.out.println("The server is waiting for client connection at port number " + port);
                
                    Socket connSocket = welcomeSocket.accept();
                    // welcomeSocket.close();

                    SocketThread sThread = new SocketThread(connSocket);
                    sThread.start();
                }
                catch (IOException e) {
                    System.out.println("Cannot create this connection\nError: " + e.getMessage());
                } 
            }
        }
}
