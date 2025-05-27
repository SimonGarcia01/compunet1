package com.example.curriculum_sis.ui;

import java.util.Scanner;

import com.example.curriculum_sis.util.UDPConnection;

public class PeerB {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5001);
        connection.start(); //This will start the thread so it starts to listen through the port

        Scanner sk = new Scanner(System.in);

        do {
            String msg = sk.nextLine();
            if(!connection.isConnectionClosed()){
                connection.sendDatagram("PeerB: " + msg, "192.168.1.11", 5000);
            }
            if (msg.equalsIgnoreCase("exit")) {
                connection.stopListening();
                break;
            }
        } while (true);

        sk.close();
    }
}
