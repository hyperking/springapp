package com.springapi.models;

// import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.springapi.models.Book;
// import com.springapi.models.AuthorsRegistry;

public class BookRegistry {

    public static HashMap<String, Book> bookRegistry = new HashMap<>();

    public Integer count() {
        return bookRegistry.size();
    }

    public Collection<Book> getAll() {
        // Returns list of books
        return bookRegistry.values();
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
