package util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread{
    private static UDPConnection instance;

    private DatagramSocket socket;

    private UDPConnection(){ 
        //private constructor for the singleton to use
    }

    public static UDPConnection getInstance(){
        if(instance == null){
            instance = new UDPConnection();
        }
        return instance;
    }

    public void setPort(int port){
        try{
            //initializes the socket with a port number
            this.socket = new DatagramSocket(port);
        } catch(SocketException e){
            //Can throw this exception if the port is already in use or is invalid
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
            try{
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

            //close the socket after receiving the message
            socket.close();
            } catch(IOException e){
                e.printStackTrace();
            }
    }

    public void close(){
        //Method to close the socket when the program is not  needed
        socket.close(); 
    }

    public void sendDatagram(String msg, String ipDest, int portDest){
        new Thread(
            () -> {
                try{
                    //get the ip address of the destination
                    InetAddress ipAddress = InetAddress.getByName(ipDest);

                    //make the datagram packet with the massage
                    DatagramPacket packet = new DatagramPacket(
                        msg.getBytes(),
                        msg.length()
                    );

                    //Connect to that ip address with the inputed port
                    socket.connect(ipAddress, portDest);

                    //send the packet with the message
                    socket.send(packet);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        ).start();
    }
}
