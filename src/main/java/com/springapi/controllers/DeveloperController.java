package com.springapi.controllers;

import com.springapi.models.Developer;
import com.springapi.models.DeveloperRegistry;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/developer")
public class DeveloperController {
    public static DeveloperRegistry developersRegistry = new DeveloperRegistry();
    private HashMap<String, Object> res = new HashMap<>();
    private Developer developer = new Developer("Derry Spann", "/images/squirtle.jpeg", "10.5.226.143");

    @RequestMapping("/me")
    @ResponseBody
    public Developer developer() {
        developer.setRole("Creative Engineer");
        developer.setBio("Witness the Awesome!");
        return developer;
    }

    @PutMapping("/like")
    @ResponseBody
    public HashMap<String, Object> likeme() {
        res.clear();
        try {
            developer.setLikes(developer.getLikes() + 1);
            res.put("status", "OK");
            res.put("likes", developer.getLikes());
        } catch (Exception e) {
            res.put("status", "failed");
            res.put("msg", e.toString());
        }
        return res;
    }

    @RequestMapping("/all")
    @ResponseBody
    public DeveloperRegistry registrants() {
        return developersRegistry;
    }

    @PostMapping("/new")
    public String register(@ModelAttribute Developer developer) {
        developersRegistry.addDeveloper(developer);
        return "redirect:/members";
    }
}