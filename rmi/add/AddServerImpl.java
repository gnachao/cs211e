import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf
{
   public double add(double a, double b) 
   {
      return (a + b);
   }

   public AddServerImpl() throws RemoteException
   {

   }
   
   public static void main(String[] args)
   {
      // LocateRegistry.createRegistry(1099);

      // SECURITY ISSUE
      // if(System.getSecurityManager() == null)
      // {
      //    System.getSecurityManager();
      // }
      // SECURITY ISSU============================= 

      try
      {
         AddServerImpl asi = new AddServerImpl();
         Naming.rebind("AddServer", asi);
         System.out.println("AddServer is ready");
      }
      catch(RemoteException e){}
      catch(MalformedURLException e){}
   }
}
