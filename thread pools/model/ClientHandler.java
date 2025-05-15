import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader clientReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
            //Get info to whatever is connection
            System.out.println("Client IP: "+ socket.getInetAddress() + " Port: " + socket.getPort());

            //Read the message that arrives through the socket
            String msg = clientReader.readLine();
            System.out.println(msg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
