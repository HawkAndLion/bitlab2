package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            Socket socket = new Socket("127.0.0.1", 2021);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true){
                System.out.println(inputStream.readObject());
                int choice = scan.nextInt();
                outputStream.writeObject(choice);
                if(choice == 1){
                    System.out.println(inputStream.readObject());

                }else if(choice == 2){
                    System.out.println(inputStream.readObject());
                    scan.nextLine();
                    String bookName = scan.nextLine();
                    outputStream.writeObject(bookName);
                    System.out.println(inputStream.readObject());
                    String author = scan.nextLine();
                    outputStream.writeObject(author);
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*public class User {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        try {
            Socket socket = new Socket("127.0.0.1", 2021);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            int bookId = 130;
            while (true) {
                System.out.println(inputStream.readObject());
                int choice = scan.nextInt();
                outputStream.writeObject(choice);
                String operationType = "QWERTY";
                String bookName = " ";
                String author = " ";
                books.add(new Book(bookId, bookName, author));
                PackageData pd = new PackageData(operationType, books);
//                if (choice == 1) {
//                    System.out.println(inputStream.readObject());
//                }else if(choice == 2){
                if (choice == 2) {
                    System.out.println(inputStream.readObject());
                    scan.nextLine();
                    //operationType = scan.nextLine;
                    //System.out.println(inputStream.readObject());
                    bookName = scan.nextLine();
                    System.out.println(inputStream.readObject());
                    author = scan.nextLine();
                    books.add(new Book(bookId, bookName, author));
//                    Book book = new Book(bookId, bookName, author);
//                    books.add(book);
                    //PackageData pd = new PackageData(operationType, books, book);
                    pd = new PackageData(operationType, books);
                    outputStream.writeObject(pd);
                    bookId++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Somethings went wrong =) or the process was finished");
        }
    }
}*/

/*
public class User {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //System.out.println("Insert your nickname");
        //String name = scan.next();
        ArrayList<Book> books = new ArrayList<>();
        try {
            Socket socket = new Socket("127.0.0.1", 2021);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println(inputStream.readObject());
                int choice = scan.nextInt();
                outputStream.writeObject(choice);
                int bookId = 130;
                if (choice == 1) {
                    System.out.println(inputStream.readObject());
                }else if(choice == 2){
                    scan.nextLine();
                    //String operationType = "Request for List of Books";
                    //System.out.println(inputStream.readObject());
                    //String operationType = scan.nextLine();
                    String operationType = " ";
                    System.out.println(inputStream.readObject());
                    String bookName = scan.nextLine();
                    System.out.println(inputStream.readObject());
                    String author = scan.nextLine();
                    Book book = new Book(bookId, bookName, author);
                    books.add(book);
                    PackageData pd = new PackageData(operationType, books, book);
                    ObjectOutputStream outStr = new ObjectOutputStream(new FileOutputStream("booksData.txt"));
                    outStr.writeObject(pd);
                    bookId++;
                    outStr.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Somethings went wrong =) or the process was finished");
        }
    }
}
*/

