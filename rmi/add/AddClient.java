import java.rmi.*;

public class AddClient
{
   public static void main(String[] args)
   {
      try
      {
         String url = "rmi://" + args[0] + "/AddServer";//will use defaul 1099 port      
         AddServerIntf asi = (AddServerIntf) Naming.lookup(url);
         double d1 = Double.parseDouble(args[1]);
         double d2 = Double.parseDouble(args[2]);
         System.out.println("sum = " + asi.add(d1, d2));
      }catch(Exception e){
         System.out.println("Error: "+e);
      }
   }
}
