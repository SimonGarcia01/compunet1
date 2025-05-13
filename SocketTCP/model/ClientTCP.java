package model;

import util.TCPConnection;

public class ClientTCP implements TCPConnection.IListener{ 
    public static void main(String[] args) {
        TCPConnection connection = TCPConnection.getInstance();
        connection.initAsClient("127.0.0.1", 5000);
        connection.sendMessage("Hello from client");

        //Add a subscriber to listen to the messages
        ServerTCP client = new ServerTCP();
        connection.setListener(client);

        connection.start();
    }

    @Override
    public String onMessage(String message){
        System.out.println(message);
        return message;
    }
}
