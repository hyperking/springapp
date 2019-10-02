package com.springapi.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.qos.logback.classic.pattern.DateConverter;

/**
 * Log
 */
@Document(collection = "logs")
public class Log {
    @Id
    private String id;

    
    private String debug_timestamp;

    private String debug_name;
    private String debug_level;
    private String debug_thread;
    private String debug_file;
    private String debug_line;
    private String debug_message;

    public Log(){}
    public Log(String debug_timestamp, String debug_name, String debug_level, String debug_thread, String debug_file, String debug_line, String debug_message) {
        this.debug_timestamp = debug_timestamp;
        this.debug_name = debug_name;
        this.debug_level = debug_level;
        this.debug_thread = debug_thread;
        this.debug_file = debug_file;
        this.debug_line = debug_line;
        this.debug_message = debug_message;
    }

    public Map<String, Object> fromJson(Object json){
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(json, Map.class);
        return map;
    }
   

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDebug_timestamp() {
        return this.debug_timestamp;
    }

    public void setDebug_timestamp(String debug_timestamp) {
        this.debug_timestamp = debug_timestamp;
        // SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        // Date newDate;
        // try {
        //     newDate = date.parse(debug_timestamp);
        //     this.debug_timestamp = newDate;
        // } catch (ParseException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

    }

    public String getDebug_name() {
        return this.debug_name;
    }

    public void setDebug_name(String debug_name) {
        this.debug_name = debug_name;
    }

    public String getDebug_level() {
        return this.debug_level;
    }

    public void setDebug_level(String debug_level) {
        this.debug_level = debug_level;
    }

    public String getDebug_thread() {
        return this.debug_thread;
    }

    public void setDebug_thread(String debug_thread) {
        this.debug_thread = debug_thread;
    }

    public String getDebug_file() {
        return this.debug_file;
    }

    public void setDebug_file(String debug_file) {
        this.debug_file = debug_file;
    }

    public String getDebug_line() {
        return this.debug_line;
    }

    public void setDebug_line(String debug_line) {
        this.debug_line = debug_line;
    }

    public String getDebug_message() {
        return this.debug_message;
    }

    public void setDebug_message(String debug_message) {
        this.debug_message = debug_message;
    }





    
}