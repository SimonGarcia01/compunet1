import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket clientSocket){
        this.socket = clientSocket;
    }

    //Has to accept the connections of the client
    @Override
    public void run() {
        try {

            //Buffer that reads the input from the client
            BufferedReader clientReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );

            //Buffer that writes the output to the client
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //Get info to whatever is connection

            String message = "";

            while((message = clientReader.readLine()) != null){
                //Print the message that arrives from the client
                System.out.println("Client IP: "+ socket.getInetAddress() + " Port: " + socket.getPort());
                
                //Echo response back to the client
                writer.println("Echo >>" + message + " IP client: " + socket.getInetAddress());

                //Read the message that arrives through the socket
                String msg = clientReader.readLine();
                System.out.println(msg);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
