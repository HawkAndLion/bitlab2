package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PackageData implements Serializable {
    private String operationType;
    private ArrayList<Book> books;
    private Book book;

    public PackageData(){
    }
    public PackageData(String operationType) {
        this.operationType = operationType;
        this.books = readList();
        this.book = null;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Book> getBooks() {
        this.books = readList();
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        writeList(books);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addBook(Book book) {
        books.add(book);
        writeList(books);
    }

    @Override
    public String toString() {
        return "OperationType: '" + operationType + '\'' +
                ", Book list: "+ books + ", Book: "+book;
    }
    public  static  void writeList(ArrayList<Book> books){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("text.txt"));
            outputStream.writeObject(books);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Book> readList (){
        ArrayList<Book> books = new ArrayList<>();
        try {
            File file = new File("text.txt");
            if(file.exists()){
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("text.txt"));
                books = (ArrayList<Book>) inputStream.readObject();
                inputStream.close();
            }else{
                System.out.println("...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
