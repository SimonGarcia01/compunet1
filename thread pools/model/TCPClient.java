import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {

        try(Socket socket = new Socket("127.0.0.1", 5000);){

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String msg="";

            while ((msg = consoleReader.readLine()) != null) {
                socketWriter.write(msg);
                socketWriter.newLine();
                socketWriter.flush();
            }

            socketWriter.close();
            consoleReader.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
