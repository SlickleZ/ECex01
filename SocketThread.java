import java.net.*;
import java.util.*;
import java.io.*;


public class SocketThread extends Thread {

    private Socket connectionSocket;

    SocketThread(Socket socket) {
        this.connectionSocket = socket;
    }

    public void run() {
        Scanner messageInScanner = null;
        DataOutputStream messageOutputStream = null;

        try {
            messageInScanner = new Scanner(connectionSocket.getInputStream());
            messageOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            
            int num1 = messageInScanner.nextInt();
            System.out.println(num1);

            messageOutputStream.writeInt(1);

            int num2 = messageInScanner.nextInt();
            System.out.println(num2);
            messageOutputStream.writeInt(num1 + num2);
        } catch (IOException | InputMismatchException e) {
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
