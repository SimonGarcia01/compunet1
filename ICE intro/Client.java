public class Client {
    public static void main(String[] args) {
        try(com.zeroc.Ice.Communicator communicator =
                com.zeroc.Ice.Util.Initialize(args)){
            
            //RPC runtime
            com.zeroc.Ice.ObjectPrx base = 
                communicator.stringToProxy("SimplePrinter: default -p 10000");
            
            //Stub
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            
            if(printer == null){
                throw new Error("Invalid Proxy");
            }

            //Request of the operation
            printer.printString("Message from the client");
        }
    }
}
