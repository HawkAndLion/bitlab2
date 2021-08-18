package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private Socket socket;
    private int id;
    ArrayList <Book> books = new ArrayList<>();
    PackageData pd;

    public ClientHandler(Socket socket,int id) {
        this.socket = socket;
        this.id = id;
    }
    public ClientHandler(Socket socket,int id,PackageData pd) {
        this.socket = socket;
        this.id = id;
        this.pd = pd;
    }

    public void run(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true){
                outputStream.writeObject("PRESS [1] TO LIST BOOKS\n"+"PRESS [2] TO ADD BOOKS\n"+"PRESS [0] TO DISCONNECT FROM SERVER");
                int choice = (int) inputStream.readObject();
                if(choice == 1){
                        outputStream.writeObject(pd.getBooks().toString());
                }else if(choice == 2){
                    outputStream.writeObject("Insert book's name:");
                    String bookName = (String) inputStream.readObject();
                    outputStream.writeObject("Insert author's name:");
                    String author = (String) inputStream.readObject();
                    pd.addBook(new Book(bookName, author));
                    //outputStream.writeObject(pd);
                }else if(choice == 0){
                    break;
                }else{
                    String text = "Wrong command!";
                    outputStream.writeObject(text);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

