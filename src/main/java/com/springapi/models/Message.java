package com.springapi.models;

// import lombok.Data;
import java.util.Date;


public class Message {

    // == fields ==
    private String username;
    private String message;
    private Date date;

    // == constructors ==
    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.date = new Date();
    }

    public Message() {}

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message username(String username) {
        this.username = username;
        return this;
    }

    public Message message(String message) {
        this.message = message;
        return this;
    }

    public Message date(Date date) {
        this.date = date;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

}//END class Message
