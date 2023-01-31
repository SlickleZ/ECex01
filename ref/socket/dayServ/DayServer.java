// DayServer.java

import java.net.*;
import java.io.*;
import java.util.Date;

public class DayServer
{
  public static final int PORT = 1666;
  public static void main(String[] args)
  {
    ServerSocket serverSocket = null;
    Socket serviceSocket = null;
    
    try
    { // create socket
      //int port = new Integer(args[0]).intValue();
      serverSocket = new ServerSocket(PORT);
    } catch (Exception e)
    {
      System.err.println("Error Creating Socket");
    }

    while (true)
    { try
      {

        // wait for connection then create streams
        System.out.println("Waiting for client connection");
        serviceSocket = serverSocket.accept();
        OutputStreamWriter output =
                         new OutputStreamWriter(serviceSocket.getOutputStream());

		Date now = new Date();

         output.write(now.toString() + "\r\n");
         output.flush();
         serviceSocket.close();

      } catch (Exception e)
      {
        System.err.println("Closing Socket connection");
        if (serviceSocket != null)
           try
           { serviceSocket.close();
           } catch (IOException ex) {}
      }
    }
  }
}

