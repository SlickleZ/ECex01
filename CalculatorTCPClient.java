import java.io.*; 
import java.net.*;
import java.util.*; 


public class CalculatorTCPClient {
    public static void main(String[] args) throws Exception {
        int num1, num2, sum;
        final int PORT = 1112;
        Scanner userListener = null;
        Scanner serverListener = null;
        Socket clientSocket = null;
        PrintStream outputStream = null;

        try {
            userListener = new Scanner(System.in);

            while(true) {
                clientSocket = new Socket("localhost", PORT);
                outputStream = new PrintStream(clientSocket.getOutputStream(), true);
                serverListener = new Scanner(clientSocket.getInputStream());

                System.out.print("Enter number 1 (To end just press enter): ");
                num1 = Integer.parseInt(userListener.nextLine());

                outputStream.println(num1);
                // System.out.println("Message1 is out!");

        
                System.out.print("Enter number 2 (To end just press enter): ");
                num2 = Integer.parseInt(userListener.nextLine());

                outputStream.println(num2);
                // System.out.println("Message2 is out!");
                
                sum = serverListener.nextInt();
                System.out.println("The result is " + sum);

                if (clientSocket != null) { clientSocket.close(); }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Connection is lost from server.");
        } finally {
            if (serverListener != null) { serverListener.close(); }
            if (outputStream != null) { outputStream.close(); }
            if (userListener != null) { userListener.close(); }
        }
    }
}
