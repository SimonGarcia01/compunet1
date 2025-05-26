import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    public static void main(String[] args) {
        
        //Create the thread pools to get the info from clients
        ExecutorService pool = Executors.newFixedThreadPool(3);
        
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            //executes one thread
            //Has to accept some type of connection
            //Socket socket = serverSocket.accept();
            // pool.execute(new ClientHandler(socket));

            //Execute multiple threads
            while (true) {
                System.out.println("Waiting for client connections...");
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected from IP: "+ socket.getInetAddress() + 
                    " Port: " + socket.getPort());

                //Create a new thread for each client connection
                pool.execute(new ClientHandler(socket));
            }
            
            


        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
