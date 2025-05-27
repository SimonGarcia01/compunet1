import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zeroc.Ice.*;

import Demo.*;

public class Client {
    public static void main(String[] args) {
        try(Communicator communicator = Util.initialize(args, "properties.cfg")){

            SubscriberI subscriber = new SubscriberI();

            ObjectAdapter adapter = communicator.createObjectAdapter("Subscriber");

            //the String to Identity is used to identify the publisher from other computers
            //In the case of the client it doesn't need to be identify, so its unnecesary the identity
            ObjectPrx proxy = adapter.add(subscriber, Util.stringToIdentity("NotNecessaryName"));

            adapter.active();

            SubscriberPrx subscriberPrx = SubscriberPrx.checkedCast(proxy);

            PublisherPrx publisher = PublisherPrx.checkedCast(communicator.propertyToProxy("publisher.proxy"));

            if(publisher == null){
                throw new Error("Bad Ice Proxy");
            }

            //Read input from the console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            String name = reader.readLine();

            //Name of the subscriber and the subscriberPrx is the reference to the subscriber within the executing machine
            publisher.addSubscriber(name, subscriberPrx);
            
            communicator.waitForShutdown();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}   
