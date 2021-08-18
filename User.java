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


