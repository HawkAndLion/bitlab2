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

/*public class ClientHandler extends Thread{
    private Socket socket;
    private int id;

    public ClientHandler(Socket socket, int id){
        this.socket = socket;
        this.id = id;
    }

    public void run(){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true){
                String menu = "PRESS [1] TO LIST BOOKS\n"+"PRESS [2] TO ADD BOOKS\n"+"PRESS [0] TO DISCONNECT FROM SERVER";
                outputStream.writeObject(menu);
                int choice = (int) inputStream.readObject();
                if(choice == 1){

                    PackageData pd = null;
                    if((pd = (PackageData) inputStream.readObject()) != null){
                        //outputStream.writeObject(pd.getBooks());
                        //outputStream.writeObject(pd.getBooks().toString());
                        //outputStream.writeObject(pd);
                        System.out.println(inputStream.readObject());
                    }else{
                        String text = "No information";
                        outputStream.writeObject(text);
                    }
                }else if(choice == 2){
                    //String operationType = "Specify the operation type (e.g. 'update')";
                    //outputStream.writeObject(operationType);
                    String bookName = "Insert name of the book:";
                    outputStream.writeObject(bookName);
                    String author = "Insert name of author:";
                    outputStream.writeObject(author);
                }else if(choice == 0){
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

/*
public class ClientHandler extends Thread{
    private Socket socket;
    private int id;
    private PackageData pd;

    public ClientHandler(Socket socket, int id, PackageData pd){
        this.socket = socket;
        this.id = id;
        this.pd = pd;
    }

    public void run(){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true){
                String menu = "PRESS 1 TO LIST BOOKS\n"+"PRESS 2 TO ADD BOOKS\n"+"PRESS [0] TO DISCONNECT FROM SERVER";
                outputStream.writeObject(menu);
                int choice = (int) inputStream.readObject();
                if(choice == 1){
                    //ObjectInputStream inStream = new ObjectInputStream( new FileInputStream("booksData"));
                    //pd = null;


//                    if((pd = (PackageData) inStream.readObject()) != null){
//                        outputStream.writeObject(pd);
//                    }else{
//                        String text = "No information";
//                        outputStream.writeObject(text);
//                    }

                    File file = new File("input.txt");
                    if(file.exists()){
                        ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("booksData.txt"));
                        pd = (PackageData) inputStream.readObject();
                    }else{
                        System.out.println("No such file!");
                    }

                    //inStream.close();

                }else if(choice == 2){
                    //String operationType = "Specify the operation type (e.g. 'update')";
                    //outputStream.writeObject(operationType);
                    String bookName = "Insert name of the book:";
                    outputStream.writeObject(bookName);
                    String author = "Insert name of author:";
                    outputStream.writeObject(author);

                }else if(choice == 0){
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
