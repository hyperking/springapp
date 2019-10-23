package com.springapi.config;

import com.springapi.controllers.ApiController;
import com.springapi.models.Book;
import com.springapi.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ControllerContext
 * This will Autowire the BookService and books into the ApiController class
 */
// @ControllerAdvice(basePackages = "com.springapi.controllers")
@ControllerAdvice(assignableTypes = ApiController.class)
public class ApiControllerContext{
    @Autowired
    public BookService bookService;

    @ModelAttribute("catalog")
    public List<Book> getCatalog()
    {
        return bookService.getDBBooks();
    }
}