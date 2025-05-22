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
        }
    }
}   
