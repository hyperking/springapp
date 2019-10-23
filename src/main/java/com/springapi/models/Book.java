package com.springapi.models;

import java.util.ArrayList;
import com.springapi.models.Author;

import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection="books")
public class Book {
    @Id
    private String id;
    private String isbn; // 32bit
    private String title;
    private String author;
    private ArrayList<String> authors = new ArrayList<>();
    private double price;
    private int stock; // 32bit
    private String discount;

    public Book(){}
    public Book(String title, String author, double price, String isbn, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = (isbn != null) ? isbn : UUID.randomUUID().toString();
        this.stock = stock;
    }
    // Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    
    public ArrayList<String> getAuthors() {
        return authors;
    }
    public void setAuthors(Author author){
        this.authors.add(author.getId());
    }
    public String getAuthor() {
        return author;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String toCurrency()
    {
        return String.format("%.2f", price);
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public int getStock() {
        return stock;
    }

    public String getDiscount(){
        return this.discount;
    }
    public void setDiscount(String discount){
        this.discount = discount;
    }
    
    // Setters
    
    public void setTitle(String title) {
        this.title = title.trim();
    }
    
    public void setAuthor(String author) {
        this.author = author.trim();
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setStock(int num) {
        this.stock = num;
    }
    
}