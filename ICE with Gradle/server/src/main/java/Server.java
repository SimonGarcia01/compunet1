import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zeroc.Ice.*;

public class Server {
    public static void main(String[] args) {
        try(Communicator communicator = Util.initialize(args, "properties.cfg")){
            
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

            PublisherI publisher = new PublisherI();

            adapter.add(publisher, Util.stringToIdentity("Publisher"));

            adapter.activate();

            //Send a message to the subcribers

            //Connect the reader to the console input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            
            System.out.println("Type a message in formate SubscriberName::Message");
            //As long as there is input from the console with the right format, keep the loop running
            while((message = reader.readLine()) != null){
                if(!message.contains("::")){
                    System.out.println("Message with incorrect format.");
                    //Skips the rest of the loop and goes to the next iteration
                    continue;
                }
                String[] command = message.split("::");
                
                publisher.notifySubscriber(command[0], command[1]);
            }

            reader.close();

            communicator.waitForShutdown();
        } catch(IOException e){
            e.printStackTrace();
        } 
    }
}
