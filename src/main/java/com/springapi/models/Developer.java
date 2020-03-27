package com.springapi.models;
// import com.springapi.models.App;

import java.util.ArrayList;
import java.util.UUID;


public class Developer {

    private String name;
    private String firstName;
    private String lastName;
    private String role;
    private String bio;
    private String picture;
    private long likes;
    private String ipAddress;
    private String email;

    public Developer(){}
    public Developer(String name, String picture, String ipAddress)
    {
        this.name = name;
        this.firstName = name;
        this.lastName = "";
        this.email = "";
		this.role = "";
		this.bio = "";
		this.picture = picture;
        this.likes = 0;
        this.ipAddress = ipAddress;
        parseName();
    }


    // Getters
    public String getEmail() {
        return email;
    }
    public String getIpaddress(){
        return ipAddress;
    }
    public String getName()
    {
        return name;
    }

    public String getRole()
    {
        return role;
    }

    public String getBio()
    {
        return bio;
    }

    public long getLikes()
    {
        return likes;
    }
    
    public String getPicture()
    {
        return picture;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String fname) {
        this.firstName = fname;  
    }
    public void setLastName(String lname) {
        this.lastName = lname;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public void setLikes(long like){
        this.likes = like;
    }

    public void setIpaddress(String ipaddress){
        this.ipAddress = ipaddress;
    }

    public void guessEmail() {
        this.email = this.firstName+"_"+this.lastName+"@bah.com".toLowerCase();
    }

    public void parseName() {
        Integer i = 1;
        String fullname = this.firstName+" "+this.lastName;
        this.name = fullname;
//        for (String part : fullname.split(" ")) {
//            switch (i) {
//            case 1:
//                this.firstName = part;
//                break;
//            case 2:
//                this.lastName = part;
//                break;
//            default:
//                this.lastName = this.lastName + " " + part;
//                break;
//            }
//            i++;
//        }

    }

}