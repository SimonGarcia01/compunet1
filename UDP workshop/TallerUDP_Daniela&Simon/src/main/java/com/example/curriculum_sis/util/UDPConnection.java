package com.example.curriculum_sis.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPConnection extends Thread{
    private static UDPConnection instance;

    private DatagramSocket socket;
    private boolean flag = true;

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
        while(flag){
            try {
                //Define a structure to store the info
                byte[] buffer = new byte[1024];

                //define the packet where the received message will be stored
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                System.out.println("Waiting for a message...");
                //the socket will then store the info in that packet
                socket.receive(packet);
                
                //Now the info must be decoded from bytes to string
                String msg = new String(packet.getData(), 0, packet.getLength()).trim();
                
                System.out.println(msg);

                if (msg.toLowerCase().endsWith("exit")) {
                    System.out.println("The other peer has closed the connection.");
                    flag = false;
                }
            } catch (SocketException se) {
                // This happens if the socket is closed
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    public void stopListening(){
        //Method to end the thread
        flag = false;
    }

    public void close(){
        //Method to close the socket when the program is not  needed
        socket.close();
    }

    public boolean isConnectionClosed(){
        return socket.isClosed();
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
                        msg.length(),
                        ipAddress,
                        portDest
                    );

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
