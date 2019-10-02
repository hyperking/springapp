package com.springapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.log4j.Logger;
/**
 * BookstoreLogger
 */
@Configuration
public class BookstoreLogger {

    @Bean
    public Logger appLogger()
    {
        return Logger.getLogger("null");
    }
}