package com.springapi.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.springframework.stereotype.Service;

import com.springapi.models.Book;
// import com.springapi.repo.BookSchema;
// import com.springapi.models.AuthorsRegistry;

@Service
public class BookServiceStatic {

    public static HashMap<String, Book> bookRegistry = new HashMap<>();

    public Integer count() {
        return bookRegistry.size();
    }
    public ArrayList<Book> getBooks(){
        // return bookRegistry.values().toArray();
        return new ArrayList<Book>( bookRegistry.values() );
    }
    public Boolean isEmpty() {
        return bookRegistry.isEmpty();
    }

    public Collection<Book> getAll() {
        // Returns list of books
        HashMap<String, Book> default_Collection = new HashMap<String, Book>();
        return isEmpty() ? default_Collection.values() : bookRegistry.values();
    }

    public Collection<String> getIds() {
        // Returns a list of ids
        return bookRegistry.keySet();
    }

    public Book getBook(String bookid) {
        // Return book associated with bookid
        if (bookid != "") {
            return bookRegistry.get(bookid);
        }
        return null;
    }

    public void addBook(Book newbook) {
        // Assign uniqe guid uses the isbn as a string and added into hashmap
        if (!this.hasBook(newbook) && newbook.getStock() > 0) {
            String guid = newbook.getIsbn().toString();
            bookRegistry.put(guid, newbook);
        }
    }

    public boolean hasBook(Book newbook) {
        // check if book is inside registry.
        if (bookRegistry.containsKey(newbook.getIsbn().toString())) {
            return true;
        }
        for (Book b : bookRegistry.values()) {
            if (b.getTitle().equals(newbook.getTitle())) {
                System.out.println("Found a duplicate book!");
                return true;
            }
        }
        return false;
    }

    public void updateBook(Book alteredbook) {
        bookRegistry.put(alteredbook.getIsbn(), alteredbook);
    }

    public void purchaseBook(String bookid, Integer quantity) {
        Book item = bookRegistry.get(bookid);
        int stock = item.getStock();
        if (stock > 0) {
            item.setStock(stock - quantity);
        }
    }

    public void deleteBook(String bookid) {
        bookRegistry.remove(bookid);
    }

}
