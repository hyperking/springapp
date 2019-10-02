package com.springapi.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.springapi.models.Log;

/**
 * FileReader
 */
public class ReadFile {
    public ArrayList<Log> jsonlog = new ArrayList<>();
    public String filepath;
    
    public ReadFile() { }
    public ReadFile(String filepath){
        ObjectMapper objectMapper = new ObjectMapper();

        this.filepath = filepath;
        try {
            FileInputStream fstream = new FileInputStream(this.filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null) {
                /* parse strLine to obtain what you want */
                 
                // Map<String, String> result = new ObjectMapper().readValue(strLine, Map.class);
                Log result = objectMapper.readValue(strLine, Log.class);
                jsonlog.add(result);
            }
            fstream.close();
            // System.out.println(this.jsonlog);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    

    public ArrayList<Log> getJsonlog() {
        return this.jsonlog;
    }

    public String getFilepath() {
        return this.filepath;
    }


}