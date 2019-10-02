package com.springapi.config;

import com.springapi.controllers.BookController;
import com.springapi.models.Book;
import com.springapi.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ControllerContext
 * 
 */
// @ControllerAdvice(basePackages = "com.springapi.controllers")
@ControllerAdvice(assignableTypes = BookController.class)
public class BookControllerContext{
    @Autowired
    public BookService bookService;

    @ModelAttribute("catalog")
    public List<Book> getCatalog()
    {
        return bookService.getDBBooks();
    }
}