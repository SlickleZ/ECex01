import java.io.*;
import java.net.*;

public class CalculatorTCPServer {
        public static void main(String[] args) {
            ServerSocket welcomeSocket = null;
            final int PORT = 1112;

            try {
                welcomeSocket = new ServerSocket(PORT);
            } catch (IOException e) {
                System.out.println("Welcome socket cannot be init.\nError: " + e.getMessage());
                System.exit(1);
            }
            
            System.out.println("The server is waiting for client connection at port number " + PORT);
            
            while(true) {
                try {
                    Socket connSocket = welcomeSocket.accept();

                    SocketThread sThread = new SocketThread(connSocket);
                    sThread.start();
                }
                catch (IOException e) {
                    System.out.println("Cannot create this connection\nError: " + e.getMessage());
                    try {
                        welcomeSocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
}
