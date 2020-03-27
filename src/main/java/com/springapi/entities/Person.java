 package com.springapi.entities;

 import javax.persistence.*;
 import java.io.Serializable;

 @Entity
 @Table(name = Person.TABLE_NAME)
 public class Person {
    public static final String TABLE_NAME = "people";
    @Id
    @GeneratedValue
    private int pid;
    private String fullName;
    private String role;
//    private String fName;
//    private String lName;
//    private String bio;
//    private String picture;
//    private long likes;
//    private String ipAddress;
//    private String email;

     @Override
     public String toString() {
         return "Person{" +
                 "pid=" + pid +
                 ", fullName='" + fullName + '\'' +
                 ", role='" + role + '\'' +
                 '}';
     }

     public int getPid() {
         return pid;
     }

     public void setPid(int pid) {
         this.pid = pid;
     }

     public String getFullName() {
         return fullName;
     }

     public void setFullName(String fullName) {
         this.fullName = fullName;
     }

     public String getRole() {
         return role;
     }

     public void setRole(String role) {
         this.role = role;
     }
 }