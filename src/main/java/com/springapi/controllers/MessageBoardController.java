package com.springapi.controllers;

import org.springframework.stereotype.Controller;
import com.springapi.models.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
@RequestMapping("/messages")
public class MessageBoardController {

    public static MessageBoard messageBoard = new MessageBoard();

    @GetMapping
    @ResponseBody
    public ArrayList<Message> getMessageArr(){return messageBoard.getMessageArr();}

    @RequestMapping
    public String addMessage(@ModelAttribute Message message){
        // message.setDate(new Date());
        messageBoard.addMessage(message); 
        return "redirect:/guestbook";
    }
}//END class MessageBoardController
