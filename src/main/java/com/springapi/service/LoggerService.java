package com.springapi.service;

import com.springapi.models.Log;
import com.springapi.repositories.mongo.LogRepo;
import com.springapi.utils.ReadFile;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    @Autowired
    private Environment env;

    @Autowired
    LogRepo loggerDB;

    public ArrayList<Log> jsonLogs = new ArrayList<>();

    /**
     * @return the jsonLogs
     */
    public ArrayList<Log> getJsonLogs() {
        jsonLogs = new ReadFile(env.getProperty("project.resource-dir") + "/com.springapi.log").getJsonlog();
        return jsonLogs;
    }

    public Integer getCount() {
        getJsonLogs();
        return jsonLogs.size();
    }

    public void addLog(){
        getJsonLogs();
        int lastLog = (jsonLogs.size() - 1);
        Log newLog = jsonLogs.get(lastLog);
        loggerDB.save(newLog);
    }

    public void addAll(){
        getJsonLogs();
        for(Log log : this.jsonLogs){
            loggerDB.save(log);
        }
    }

    
}
