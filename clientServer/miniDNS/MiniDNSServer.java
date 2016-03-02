//  Author:   Gna Chao
//  Date  :   2/15/2016
//  Homework assignment : 1
//  Objective : This server program is waiting to accept client 
//              request and assign work to MiniDNSClientHandler.
//****************************************************************

import java.net.*;

public class MiniDNSServer {

  //*****************************println()*****************************
  public static void println (Object o)
  {
    System.out.println( "" + o );	
  }
   //*****************************main()*****************************
  public static void main(String args[])
  {
    try
    {
      String portNumStr = System.getProperty("port", "6002");
      int port = 6002;

      try
      {
        port = Integer.parseInt(portNumStr);
      }
      catch(Exception e)
      {
        println("invalid port number " + portNumStr); 
        System.exit(1);
      }
      
      ServerSocket ss = new ServerSocket(port);
      for(;;)
      {
        Socket s = ss.accept();
        MiniDNSClientHandler ch = new MiniDNSClientHandler(s, ss);
        Thread t = new Thread(ch);
        t.start();
      }
    }catch(Exception e){}
  }
}
