package ui;

import util.UDPConnection;

public class PeerB {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        
        String msg = "Hello from PeerB";
        connection.setPort(5001); 
        //set port for PeerB can send and receive messages
        //though it will listen through port 5001
        
        connection.sendDatagram(msg, "127.0.0.1", 5000);
        connection.start(); //This starts thread so it start to listen
    }
}
