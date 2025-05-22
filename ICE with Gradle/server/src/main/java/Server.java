import com.zeroc.Ice.*;

public class Server {
    public static void main(String[] args) {
        try(Communicator communicator = Util.initialize(args, "properties.cfg")){
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            PublisherI publisher = new PublisherI();

            adapter.add(publisher, Util.stringToIdentity("Publisher"));

            adapter.activate();

            publisher.notifySubscriber("subs1", "Hello from server side.");

            communicator.waitForShutdown();
        }
    }
}
