package ui;

import util.UDPConnection;

public class PeerA {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5000);
        connection.start(); //This will start the thread so it starts to listen through the port
        // connection.close(); //This will close the socket at the end of the program 

        String msg = "Hello from PeerA";
        connection.sendDatagram(msg, "127.0.0.1", 5001);
    }
}
