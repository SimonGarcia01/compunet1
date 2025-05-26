import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {

        try{

            //Buffer that reads the console input
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter server IP address: ");
            String ipServer = consoleReader.readLine();

            System.out.println("Enter the server port: ");
            int portServer = Integer.parseInt(consoleReader.readLine());


            System.out.println("Connecting .........");
            //Make the soccet with the info
            Socket socket = new Socket(ipServer, portServer);

            //Buffer that reads the conexion from the client's socket
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Buffer that connects with our socket to write information to the NIC that eventually reaches the server
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //Other option so you don't need to flush the buffer
            //PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);

            String msg="";

            while ((msg = consoleReader.readLine()) != null && !msg.equalsIgnoreCase("exit")) {
                socketWriter.write(msg);
                //the buffer must be flushed to ensure that the data is cleaned after its used
                socketWriter.flush();
                String response = socketReader.readLine();
                System.out.println("Server response: " + response);
            }

            socketWriter.close();
            socketReader.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
