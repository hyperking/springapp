package com.springapi.controllers;

import com.springapi.models.Author;
import com.springapi.models.Book;
import com.springapi.service.BookService;
import com.springapi.service.LoggerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.HashMap;

@RequestMapping("/api")
@Controller
@CrossOrigin
public class ApiController {

    @Autowired
    BookService bookCatalog;

    @Autowired
    LoggerService logDB;

    public static final Logger logger = Logger.getLogger(ApiController.class);

    private HashMap<String, Object> api_res = new HashMap<>();


    // Section: API requests -----------------------------

    @RequestMapping("/catalog/books")
    @ResponseBody
    public Collection<Book> getBooks() {
        return bookCatalog.getDBBooks();
    }
    @RequestMapping("/catalog/authors")
    @ResponseBody
    public Collection<Author> getAuthors() {
        return bookCatalog.getDBAuthors();
    }

    @PostMapping("/catalog/addbook")
    @ResponseBody
    public HashMap<String, Object> addItem(@RequestBody Book newbook) {
        logger.info("Added new book");
        bookCatalog.addBook(newbook);
        api_res.put("message", "book added");
        return api_res;
    }

    @PostMapping("/catalog/updatebook")
    @ResponseBody
    public String updateBook(@ModelAttribute Book alteredBook) {
        bookCatalog.updateBook(alteredBook);
        return "catalog";
    }

    @RequestMapping("/catalog/{bookid}")
    @ResponseBody
    public Book getBook(@PathVariable String bookid) {
        return bookCatalog.getBook(bookid);
    }

    // @PutMapping("/catalog/purchase/{bookid}/{quantity}")
    @PostMapping("/catalog/purchase/{bookid}/{quantity}")
    @ResponseBody
    public HashMap<String, Object> purchase(@PathVariable String bookid,
            @PathVariable(required = false, value = "1") Integer quantity) {
        api_res.clear();
        Integer qty = quantity != null ? quantity : 1;
        bookCatalog.purchaseBook(bookid, qty);

        logger.info("Channeling Tim for help.");
        return api_res;
    }

    @PostMapping("/catalog/delete/{bookid}")
    @ResponseBody
    public HashMap<String, Object> delete(@PathVariable String bookid) {
        api_res.clear();
        bookCatalog.deleteBook(bookid);
        api_res.put("message", "book deleted");
        return api_res;
    }

}