package com.springapi.drools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.springapi.facts.BookFact;
import com.springapi.models.Book;
import com.springapi.service.DroolsService;

import org.junit.Test;
/**
 * DroolsTest
 */
public class DroolsTest {
    @Test
    public void applyDiscount(){
        //Setup Service
        DroolsService droolsService = new DroolsService();
        //Setup Fact
        BookFact bookFact = new BookFact();
        bookFact.setHasDiscount("Yep");

        //Setup Object
        Book book = new Book();
        book.setPrice(222);
        String result = droolsService.calculateDiscount(bookFact, book);
    
        assertNotNull(result);
        assertEquals(String.valueOf("Yep"), result);
    }
    
}

