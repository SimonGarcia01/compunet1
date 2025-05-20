import com.zeroc.Ice.ObjectAdapter;

public class Server {
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = 
        com.zeroc.Ice.Util.initialize(args)){
            //Makes the RPC runtime
            com.zeroc.Ice.ObjectAdapter adapter = 
                communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000");
            
            //Server Stub
            com.zeroc.Ice.Object object = new AppI();
            adapter.add(object, com.zeroc.Ice.Util.StringToIdentity("SimplePrinter"));
            adapter.activate();
            communicator.waitForShutDown();
        }
    
    }
}
