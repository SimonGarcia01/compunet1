import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) {
        
        try(DatagramSocket socket = new DatagramSocket();){
            String msg = "Hola desde el sender";

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            msg = reader.readLine();

            DatagramPacket packet = new DatagramPacket(
                msg.getBytes(), 
                msg.length()
                );
            //                                            dest. ip, dest. port
            socket.connect(InetAddress.getByName("192.168.131.160"), 5000);
            
            //the info is packaged and then sent
            socket.send(packet);
        } catch(Exception e){
            e.printStackTrace();
        }
        /* This doesn't work in later versions of java
        catch(SocketException | UnknownHostException er){
            er.printStackTrace();
        }
        */



    }
}
