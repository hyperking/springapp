package com.springapi.config;

import com.springapi.models.User;

// import java.util.ArrayList;
// import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
// import javax.validation.Valid;

import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ControllerContext
 */
@ControllerAdvice
public class ControllerContext {
    @ModelAttribute("user")
    public User userSession(HttpSession session, User newUser)
    {
        // Validate user and save to model
        // newUser.setTheUsername();
        // User newUser = new User("SpringUser","DemoDefault", null, null); //Default user
        return newUser;
        // return (User) session.getAttribute("user");
    }

    @ModelAttribute("userMessages")
    public Model userMessages(HttpSession session, Model messages) {
        return messages;
    }
    
    // @ModelAttribute("userMessages")
    // public Map<String,Object> userMessages(HttpSession session)
    // {
    //     @SuppressWarnings("unchecked")
    //     Map<String,Object> messages = (Map<String,Object>) session.getAttribute("my_session_msgs");
    //     return messages;
    // }
}