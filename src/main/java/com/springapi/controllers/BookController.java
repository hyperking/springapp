package com.springapi.controllers;

import com.springapi.models.Book;
import com.springapi.service.BookService;
import com.springapi.service.LoggerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;

// import com.springapi.models.BookRegistry;

// @RequestMapping("/api")
@Controller
@CrossOrigin
public class BookController {

    @Autowired
    BookService catalogService;

    @Autowired
    LoggerService logDB;

    public static final Logger logger = Logger.getLogger(BookController.class);

    private HashMap<String, Object> api_res = new HashMap<>();
    // private HashMap<String, Object> userSession = new HashMap<>();

    // Section: GET requests -----------------------------
    @RequestMapping("/catalog")
    public String catalog_home(Model ctx) {
        ctx.addAttribute("template", "catalog.html");
        ctx.addAttribute("catalog", catalogService.getDBBooks());
        logger.info("grabbing book of spells!!");
        try {
            logDB.addLog();
        } catch (Exception e) {
        }

        return "index";
    }

    @PostMapping("/catalog/purchase/{bookid}/{quantity}")
    public String purchaseBook(@PathVariable String bookid, @PathVariable Integer quantity,
            @RequestParam(required = false) String redirect) {
        Integer qty = quantity != null ? quantity : 1;
        catalogService.purchaseBook(bookid, qty);
        return "redirect:/" + redirect;
    }

    @PostMapping("/catalog/delete/{bookid}")
    public String deleteBook(@PathVariable String bookid, @RequestParam String redirect) {
        String rd = (redirect != null) ? redirect : "";
        catalogService.deleteBook(bookid);
        return "redirect:/" + rd;
    }

    @PostMapping("/catalog/updatebook")
    public String updatetheBook(@ModelAttribute Book alteredBook) {
        String rd = updateBook(alteredBook);
        return "redirect:/" + rd;
    }

    @RequestMapping(value = "/catalog/addbook", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Book newbook, @RequestParam(required = false) String redirect,
            @RequestBody MultiValueMap<String, String> formdata) {
        String rd = redirect != null ? redirect : "";
        catalogService.addBook(newbook);

        // staticBooks.addBook(newbook);
        logger.error("Invoking souls from the beyond");

        // Sentry.capture("New Book Added");
        return "redirect:/" + rd;
    }

    // Section: API requests -----------------------------

    @RequestMapping("/api/catalog/books")
    @ResponseBody
    public Collection<Book> getBooks() {
        return catalogService.getDBBooks();
    }

    @PostMapping("/api/catalog/addbook")
    @ResponseBody
    public String addItem(@ModelAttribute Book newbook) {
        catalogService.addBook(newbook);
        // staticBooks.addBook(newbook);
        return "book added!";
    }

    @PostMapping("/api/catalog/updatebook")
    @ResponseBody
    public String updateBook(@ModelAttribute Book alteredBook) {
        catalogService.updateBook(alteredBook);
        // staticBooks.updateBook(alteredBook);
        return "catalog";
    }

    @RequestMapping("/api/catalog/{bookid}")
    @ResponseBody
    public Book getBook(@PathVariable String bookid) {
        return catalogService.getBook(bookid);
    }

    // @PutMapping("/api/catalog/purchase/{bookid}/{quantity}")
    @PostMapping("/api/catalog/purchase/{bookid}/{quantity}")
    @ResponseBody
    public HashMap<String, Object> purchase(@PathVariable String bookid,
            @PathVariable(required = false, value = "1") Integer quantity) {
        api_res.clear();
        Integer qty = quantity != null ? quantity : 1;
        catalogService.purchaseBook(bookid, qty);

        logger.info("Channeling Tim for help.");
        return api_res;
    }

    @PostMapping("/api/catalog/delete/{bookid}")
    @ResponseBody
    public HashMap<String, Object> delete(@PathVariable String bookid) {
        api_res.clear();
        catalogService.deleteBook(bookid);
        api_res.put("message", "book deleted");
        return api_res;
    }

}