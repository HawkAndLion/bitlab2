package com.company;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert your name");
        String name = scan.next();

        try {
            Socket socket = new Socket("127.0.0.1", 1985);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println(inputStream.readObject());
                int choice = scan.nextInt();
                outputStream.writeObject(choice);
                if (choice == 1) {
                    System.out.println(inputStream.readObject());
                    scan.nextLine();
                    String message = scan.nextLine();
                    Date date = new Date();
                    MessageData md = new MessageData(name, message, date);
                    ArrayList array = readArray();
                    array.add(md);
                    saveClients(array);
                } else if (choice == 3) {
                   ArrayList array = readArray();
                    for (int i = 0; i < array.size(); i++) {
                        System.out.println(array.get(i));
                    }

                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("The process is finished");
        }
    }

    public static void saveClients(ArrayList array) {
        try {
            ObjectOutputStream outStream2 = new ObjectOutputStream(new FileOutputStream("message.txt"));
            outStream2.writeObject(array);
            outStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  ArrayList readArray (){
        ArrayList array = new ArrayList();
        try {
            ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("message.txt"));
            array = (ArrayList) inputStream2.readObject();
            inputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
}
