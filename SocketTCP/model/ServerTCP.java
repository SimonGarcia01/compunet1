package model;

import util.TCPConnection;

public class ServerTCP implements TCPConnection.IListener{
    public static void main(String[] args) {
        TCPConnection connection = TCPConnection.getInstance();



        //Start hearing with the TCP connection through port 5000
        connection.initAsServer(5000);
        
        //Add a subscriber to listen to the messages
        ServerTCP server = new ServerTCP();
        connection.setListener(server);

        connection.start();

        //Se envia este mensaje a traveś del TCP connection usando el puerto 5000
        connection.sendMessage("Hello from the server");


    }

    @Override
    public String onMessage(String message){
        System.out.println(message);
        return message;
    }
}
