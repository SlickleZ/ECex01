import java.net.*;
import java.util.*;
import java.io.*;


public class SocketThread extends Thread {

    private Socket connectionSocket;

    public SocketThread(Socket socket) {
        this.connectionSocket = socket;
    }

    public void run() {
        Scanner messageInScanner = null;
        PrintStream messageOutputStream = null;

        try {
            messageInScanner = new Scanner(connectionSocket.getInputStream());
            messageOutputStream = new PrintStream(connectionSocket.getOutputStream(), true);
            
            // System.out.println("Waiting for number 1");
            int num1 = messageInScanner.nextInt();
            // System.out.println(num1);

            // System.out.println("Waiting for number 2");
            int num2 = messageInScanner.nextInt();
            // System.out.println(num2);

            messageOutputStream.println(num1 + num2);
            // System.out.println("respond (sum) is send");
        } catch (IOException | NoSuchElementException e) {
            System.out.println("Closing socket connection\nErr: " + e.getMessage());
        } finally {
            try {
                if (connectionSocket != null) { connectionSocket.close(); }
                if (messageInScanner != null) { messageInScanner.close(); }
                if (messageOutputStream != null) { messageOutputStream.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
