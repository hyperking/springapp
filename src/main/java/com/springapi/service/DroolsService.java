package com.springapi.service;

import com.springapi.facts.BookFact;
import com.springapi.models.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

@Service
public class DroolsService {
 
    @Autowired
    private KieContainer kieContainer;
 
    public String calculateDiscount(BookFact bookFact, Book book) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("book", book);
        kieSession.insert(bookFact);
        kieSession.fireAllRules();
        kieSession.dispose();
        return book.getDiscount();
    }
}