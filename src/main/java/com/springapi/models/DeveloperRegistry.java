package com.springapi.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.net.URI;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.springapi.models.Developer;

/**
 * DeveloperRegistry
 */
public class DeveloperRegistry {
    public static HashMap<String, Developer> developersRegistry = new HashMap<>();
    
    public void addDeveloper(Developer developer){
        String guid = UUID.randomUUID().toString();
        HashMap<String,Object> demonName = fetch("http://nameapp:8080/nameGen/"+developer.getName(), "GET");
        System.out.println(demonName.get("demonName"));
        developer.setName((String)demonName.get("demonName"));
        developersRegistry.put(guid, developer);
    }
    public Integer count(){
        return developersRegistry.size();
    }
    public Collection<Developer> getAll(){
        return developersRegistry.values();
    }

    public HashMap<String, Object> fetch(String endpoint, String method) {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> response = new HashMap<>();
        try {
            if (method.equals("POST")) {
                response = restTemplate.postForObject(endpoint,null, HashMap.class);
            // }else if (method.equals("PUT")) {
            //     response = restTemplate.put(endpoint, null,HashMap.class);
            }else{
                response = restTemplate.getForObject(endpoint, HashMap.class);
            }
        } catch (Exception e) {
            System.out.println("Failed to grab developer " + endpoint);
            System.out.println(e);
        }
        return response;
    }

    public Developer findTeam(String name, ArrayList<Developer> team) {
        Developer member = new Developer();
        for (Developer x : team) {
            if (x.getName().equals(name)) {
                member = x;
                break;
            }
        }
        return member;
    }
}