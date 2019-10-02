package com.springapi.models;

import java.util.ArrayList;
import java.util.Date;

public class MessageBoard {

    private ArrayList<Message> messageArr = new ArrayList<Message>() {{
        add(new Message("Tim", "greetings human") );
    }};

    // GET request
    public ArrayList<Message> getMessageArr() {
        return messageArr;
    }

    //POST request
    public void addMessage(Message newMessage) {
        messageArr.add(newMessage);
    }
}//END class MessageBoard
