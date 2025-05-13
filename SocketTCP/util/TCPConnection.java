package util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//publisher class
public class TCPConnection extends Thread{
    private static TCPConnection instance;

    private TCPConnection(){};

    //subscriber / listener
    private IListener listener;

    private Socket socket;

    public static TCPConnection getInstance(){
        if(instance == null){
            instance = new TCPConnection();
        }

        return instance;
    }

    public void initAsServer(int port){
        try{
            this.socket = new ServerSocket(port).accept();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void initAsClient(String  ip, int port){
        try{
            this.socket = new Socket(ip, port);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Notify subscriber method
    public void sendMessage(String message){
        new Thread(
            () -> {
                try {
                    //Source of information that comes from the socket
                    OutputStream out = socket.getOutputStream();
                    //Packages the source of information to the java language
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
                    //Buffered writer actually writes the information on the NIC
                    //The NIC will take that written info to work with the info in bytes
                    BufferedWriter writer = new BufferedWriter(outputStreamWriter);

                    //actually write the info
                    writer.write(message + "\n");

                    //empty the writer
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        ).start();
    }

    @Override
    public void run(){
        try {
            //Connect to the source that is getting the information
            InputStream in = socket.getInputStream();

            //package the information that comes from the socket
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            //make the reader the can read the infor frmo the inputStream
            BufferedReader reader = new BufferedReader(inputStreamReader);

            //Read the information that's comming in
            String message = reader.readLine();

            //Pass on the info to the class that actually should get the message
            listener.onMessage(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Subscriber interface
    @FunctionalInterface
    public interface IListener{
        String onMessage(String message);
    }

    //add subscriber method
    public void setListener(IListener listener){
        this.listener = listener;
    }
}
