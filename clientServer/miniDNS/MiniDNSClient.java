//  Author:   Gna Chao
//  Date  :   2/15/2016
//  Homework assignment : 1
//  Objective : This client program is letting user to type request convertion
//              between ip and domain name.
//****************************************************************
import java.io.*;
import java.util.*;
import java.net.*;

public class MiniDNSClient 
{
  public static void die(String str)
  {
    String errmsg = str.length() == 0 ? "" : str;
    System.err.println(errmsg);
    System.exit(1);
  }
    
  public static void println (Object o)
  {
    System.out.println( "" + o );	
  }
    
  public static void main(String args[])
  {
    String str;
    
    if(args.length == 0) 
      die("Please provide ip address parameter of the MiniDNS Server.");
    try
    {
      Socket s = new Socket(args[0], 6002);
      println("connected to MiniDNS host " + s.getInetAddress());
      Scanner sc = new Scanner(s.getInputStream());
      PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

      Scanner sc1 = new Scanner(System.in);
      while(true)
      {
        System.out.print("Enter IpAddress/DomainName/stop\t: "); 
        System.out.flush();
        if(sc1.hasNext())
        {
          str = sc1.nextLine();
          if(str.equals("stop")) break;
          pw.println(str);
          println(sc.nextLine());
        }
      }
      println("Thank you for using MiniDNS Client. Good bye.");
      sc.close(); pw.close(); s.close(); sc1.close();
    } catch(Exception e){e.printStackTrace();}
  }  
}
