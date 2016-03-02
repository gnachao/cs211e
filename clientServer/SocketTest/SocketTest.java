/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author macbkpro
 */
public class SocketTest {

  

  public static void whoIs(String dom, int portNum)
  {
    int c;
    try(Socket s = new Socket(dom, portNum))
    {
      InputStream in = s.getInputStream();
      OutputStream out = s.getOutputStream();
      
      String str = dom.length() == 0 ? "ccsf.edu" : dom;

      byte buf[] = str.getBytes();
      out.write(buf);
      
//      while((c = in.read()) != -1) println(c); //print((char) c);
      
    } catch (Exception e){}
  }
  
  public static void getServerTime(String a, int portNum)// throws IOException
  {
    try (Socket s = new Socket(a, portNum))
    {
      InputStream inStream = s.getInputStream();
      Scanner in = new Scanner(inStream);
          
      while (in.hasNextLine()) println(in.nextLine());
    } catch(IOException e){}    
  }
  
  public static void inetAddress() throws UnknownHostException
  {
    InetAddress a = InetAddress.getLocalHost(); // using factory method to create object 
    println(a);

    a = InetAddress.getByName("hills.ccsf.edu");
    println(a);

    InetAddress x[] = InetAddress.getAllByName("www.nba.com");
    for(InetAddress k:x) println(k);
  }
  
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
    
  public static String ip2dom(String ipStr) throws UnknownHostException
  {
    String retval = "";
    StringTokenizer st = new StringTokenizer(ipStr, ".");
    if (st.countTokens() == 4)
    {
      byte[] bytes = new byte[4];
      for (int i = 0; i < 4; i++)
      {
          bytes[i] = (byte)Integer.parseInt(st.nextToken());
      }
      
      InetAddress a = InetAddress.getByAddress(bytes);
      retval = a.getHostName();
    }
    return retval;
  }
  
  public static String dom2ip(String domStr) throws UnknownHostException
  {
    InetAddress a = InetAddress.getByName(domStr);
    return a.getHostAddress();
  }
  
  public static String dom2ips(String domStr) throws UnknownHostException
  {
    InetAddress [] a = InetAddress.getAllByName(domStr);
    List<String> ips = new ArrayList();
    
    for(InetAddress ip : a)
      ips.add(ip.getHostAddress());

    return ips.toString();
  }

/**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException{
    // "time-A.timefreq.bldrdoc.gov", 13
    // getServerTime("whois.internet.net", 43);
    inetAddress();
//    whoIs("whois.internet.net", 43);
  }
}
