package com.springapi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.apache.log4j.Logger;

// import com.springapi.models.User;
import com.springapi.service.AuthorService;
/**
 * WebController
 * this controller class only returns HTML responses to the web client.
 */
import com.springapi.service.BookService;

@Controller
@SessionAttributes("user")
public class WebController {

    @Autowired
    BookService books;

    @Autowired
    AuthorService authorsService;

    public static final Logger logger = Logger.getLogger(WebController.class);

    @RequestMapping("/")
    public String homepage(Model ctx){
        // ctx.addAttribute("user", "default user");
        ctx.addAttribute("template", "home.html");
        logger.info("REQUEST MADE to home page");
        return "index";
    }
    
    @RequestMapping("/{path}")
    public String home(Model ctx, @PathVariable(required = false) String path, HttpServletRequest request) {
        String reqString = request.getRequestURI().replace("/","");
        String template = (reqString != null && !reqString.isEmpty()) ? path + ".html" : "home.html";
        if(template.equals("add-book.html") ){
            ctx.addAttribute("authors", authorsService.getDBauthors());
        }
        ctx.addAttribute("template", template);
        ctx.addAttribute("catalog", books.getDBBooks());
        return "index";
    }

    
}