//  Author:   Gna Chao
//  Date  :   2/15/2016
//  Homework assignment : 1
//  Objective : This program is doing the conversion between ip
//              address and domain name that assign by a thread 
//              of server program, MiniDNSServer
//****************************************************************

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniDNSClientHandler implements Runnable
{
  protected Socket s;
  protected ServerSocket ss;
  String str, retStr;

  public MiniDNSClientHandler(Socket st, ServerSocket sst)
  {
    s = st;
    ss = sst;
  }

  //*****************************println()*****************************
  public static void println (Object o)
  {
    System.out.println( "" + o );	
  }
    
  //*****************************isIp()*****************************
  public static boolean isIp(String s)
  {
    final String IP_ADDR_PATTERN = 
      "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
      "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
      "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
      "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    Pattern patt = Pattern.compile(IP_ADDR_PATTERN);
    Matcher matcher = patt.matcher(s);
    return matcher.matches();
  }
  //*****************************dom2ip()*****************************
  public static String dom2ip(String domStr)
  {
    try
    {
      return InetAddress.getByName(domStr).getHostAddress();
    }
    catch(Exception e)
    {
      return domStr + " could not be found.";
    }
  }
  //*****************************ip2host()*****************************
  public static String ip2host(String ipAddrStr)
  {
    try
    {
      return InetAddress.getByName(ipAddrStr).getHostName();
    }
    catch(Exception e)
    {
      return ipAddrStr + " could not be found.";
    }
  }
  //*****************************run()*****************************
  public void run()
  {
    try
    {
      Scanner sc = new Scanner(s.getInputStream()); // from client
      PrintWriter pw = new PrintWriter(s.getOutputStream(), true); // to client

      while(sc.hasNext())
      {
        str = sc.nextLine();
        if(str.equals("cs211e_stop"))
        {
          sc.close(); pw.close(); s.close(); ss.close();
        }
        retStr = isIp(str) ? ip2host(str) : dom2ip(str);
        pw.println(retStr);
      }     
    }
    catch(Exception e){e.printStackTrace();}
  } 
}
