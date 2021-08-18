package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) {
        try {
            ServerSocket server= new ServerSocket(2021);
            int id = 1;
            PackageData pd = new PackageData("Add List of Books");
            while (true){
                Socket socket = server.accept();
                System.out.println("User #"+id+ " connected");
                ClientHandler ch = new ClientHandler(socket, id, pd);
                ch.start();
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
