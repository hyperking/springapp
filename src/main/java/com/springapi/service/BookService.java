package com.springapi.service;

import com.springapi.models.Author;
import com.springapi.models.Book;
import com.springapi.repositories.mongo.AuthorsRepo;
import com.springapi.repositories.mongo.BookRepo;

import java.util.List;
import org.springframework.stereotype.Service;
// import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import static org.springframework.data.mongodb.core.query.Update.update;

@Service
public class BookService {

    @Autowired
    public BookRepo booksDB;

    @Autowired
    public AuthorsRepo authorsDB;
    
    public List<Book> getDBBooks()
    {
        return booksDB.findAll();
    }
    public List<Author> getDBAuthors()
    {
        return authorsDB.findAll();
    }
    public Long count() {
        // return bookRegistry.size();
        return booksDB.count();
    }

    public Book getBook(String bookid) {
        // Return book associated with bookid
        if (null != bookid) {
            return booksDB.findByIsbn(bookid);
        }
        return null;
    }

    public void addBook(Book newbook) {
        // Assign uniqe guid uses the isbn as a string and added into hashmap
        if (!booksDB.existsById(newbook.getIsbn()) && newbook.getStock() > 0) 
        {
            Author newAuthor = new Author();
            newAuthor.parseName( newbook.getAuthor() );
            Author bookAuthor = authorsDB.save(newAuthor);
            newbook.setAuthors(bookAuthor);
            booksDB.save(newbook);
        }
    }
    

    public void updateBook(Book alteredBook) {
        // Book dbbook = booksDB.findByIsbn(alteredBook.getIsbn());
        // alteredBook.setId(dbbook.getId());
        booksDB.save(alteredBook);
    }

    public void purchaseBook(String bookisbn, Integer quantity) {
        Book purbook = booksDB.findByIsbn(bookisbn);
        int stock = purbook.getStock();
        if (stock > 0) {
            purbook.setStock(stock - quantity);
        }
        updateBook(purbook);
    }

    public void deleteBook(String bookid) {
        booksDB.delete(booksDB.findByIsbn(bookid));
    }

}
