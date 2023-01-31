import java.io.*; 
import java.net.*;
import java.util.*; 


public class CalculatorTCPClient {
    public static void main(String[] args) throws Exception {
        int num1, num2, sum, port = 1112;
        Scanner userListener = null;
        Scanner serverListener = null;
        Socket clientSocket = null;
        DataOutputStream outputStream = null;
        
        try {
            userListener = new Scanner(System.in);
            clientSocket = new Socket("localhost", port);
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            serverListener = new Scanner(clientSocket.getInputStream());

            while(true) {
                System.out.print("Enter number 1 (To end just press enter): ");
                num1 = userListener.nextInt();

                outputStream.writeInt(num1);

                if (serverListener.nextInt() == 1) {
                    System.out.print("Enter number 2 (To end just press enter): ");
                    num2 = userListener.nextInt();
                    outputStream.writeInt(num2);
                    
                    sum = serverListener.nextInt();
                    System.out.println("The result is " + sum);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred: Connection is lost from server.");
        } finally {
            try {
                if (serverListener != null) { serverListener.close(); }
                if (outputStream != null) { outputStream.close(); }
                if (clientSocket != null) { clientSocket.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
