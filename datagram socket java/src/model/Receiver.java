import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.DatagramPacket;


public class Receiver {
    public static void main(String[] args) {
        //                                             dest. port
        //                                  the port was enabled to listen to info
        try(DatagramSocket socket = new DatagramSocket(5000)){
            //Define a structure to store the info
            byte[] buffer = new byte[30];

            //define the packet where the received message will be stored
            DatagramPacket packet = new DatagramPacket(buffer, 30);

            System.out.println("Waiting for a message...");
            
            //The socket makes a conection to the senter
            //Then it will store the info in that packet
            socket.receive(packet);

            System.out.println("Message from sender: ");

            //Decode the info from bytes to string
            String msg = new String(packet.getData()).trim();
            System.out.println(msg);

        }catch(Exception e){

        }
    }
}
