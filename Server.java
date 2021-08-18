package com.company;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ArrayList array = new ArrayList();
        try{
            ServerSocket server = new ServerSocket(1985); //создается объект класса ServerSocket
            Socket socket = server.accept(); //вот тут наш сервер ждет подключения от клиента и если кто-то подключается создается Socket и мы дальше будем с ним работать.
            System.out.println("Client connected");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream()); //мы создаем объект класса ObjectInputStream на основе нашего socket, чтобы принимать входящие сообщения
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true){
                String menu = "PRESS [1] TO SEND MESSAGE\n"+"PRESS [2] TO EXIT\n"+"PRESS [3] TO SEE MESSAGE\n";
                outputStream.writeObject(menu);
                int choice = (int) inputStream.readObject();
                if(choice == 1){
                    String text1 = "Insert message text:";
                    outputStream.writeObject(text1);
                    MessageData md = null;
                    if ((md = (MessageData) inputStream.readObject()) != null){
                        System.out.println(md);
                    }
                }else if(choice == 2){
                    break;
                }else if(choice == 3){

                    ObjectInputStream inpStream2 = new ObjectInputStream(new FileInputStream("message.txt"));
                    array = (ArrayList) inpStream2.readObject();
                    inpStream2.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
