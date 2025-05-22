import java.util.HashMap;
import com.zeroc.Ice.Current;
import Demo.SubscriberPrx;
import Demo.PublisherPrx;

public class PublisherI implements Demo.Publisher{
    private HashMap<String, SubscriberPrx> subscribers;

    public PublisherI(){
        subscribers = new HashMap<>();
    }

    @Override
    public void addSubscriber(String name, SubscriberPrx subscriber, 
    Current current){
        System.out.println("New Subscriber has been added");

        subscribers.put(name, subscriber);
    }

    @Override
    public void removeSubscriber(String name, Current current){
        subscribers.remove(name);
        System.out.println("The subscriber "+ name+" has been removed.");
    }

    public void notifySubscriber(String name, String message){
        SubscriberPrx subscriber = subscribers.get(name);
        subscriber.onUpdate(message);
    }
}
