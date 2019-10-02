package com.springapi.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.springapi.models.User;
import com.springapi.service.UsersService;
import com.springapi.utils.SlackAuth;
/**
 * UserController
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    @Autowired
    UsersService usersDB;

    @RequestMapping(value = "/oauth", method=RequestMethod.GET)
    public String userOAuth(Model ctx, @RequestParam HashMap<String, String> params){
        SlackAuth slack = new SlackAuth();
        HashMap<String, Object> res = slack.getAccessToken(params);
        ctx.addAttribute("template", "oauth.html");
        ctx.addAttribute("slack", res);
        return "user/index";
    }

    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile(Model ctx){
        ctx.addAttribute("template", "profile.html");
        return "user/index";
    }
    
    @RequestMapping(value = "/sign-out", method=RequestMethod.GET)
    public String userSignOut(Model ctx)
    {
        ctx.addAttribute("user", new User() );
        return "redirect:/";
    }
    
    @RequestMapping(value = "/sign-in", method=RequestMethod.GET)
    public String userSignIn(Model ctx) {
        ctx.addAttribute("template", "sign-in");
        return "user/index";
    }
    
    @RequestMapping(value = "/sign-in", method=RequestMethod.POST)
    public String userSignInHandler(Model ctx, 
    @RequestParam Map<String, String> user, HttpSession session){
        User loginUser = usersDB.authUser(user);
        if(loginUser.getEmail()== null){
            ctx.addAttribute("error", "unable to sign in with those credentials");
            ctx.addAttribute("template", "sign-in.html");
            return "/user/index";
        }else{
            ctx.addAttribute("user", loginUser);
            return "redirect:/user/profile";
        }
    }

    @RequestMapping(value = "/sign-up", method=RequestMethod.GET)
    public String userSignUp(Model ctx){
        ctx.addAttribute("template", "sign-up.html");
        return "user/index";
    }

    
    @RequestMapping(value="/sign-up", method=RequestMethod.POST)
    public String userSignUp(Model ctx, 
    User user, BindingResult formResult) {
        // userSignUpApi(user);
        user.setTheUsername();
        if ( formResult.hasErrors() ) {
            ctx.addAttribute("userMessages", formResult.getFieldErrors());
            ctx.addAttribute("template", "sign-up.html");
            return "/user/index";
        } else if( usersDB.userExist(user.getEmail()) ){
            ctx.addAttribute("userMessages", "User already exists");
        } else {
            User newUser = usersDB.register(user);
            ctx.addAttribute("userMessages", newUser);
        }
        return "redirect:/user/profile";
    }

    @RequestMapping(value = "/api/sign-up", method=RequestMethod.POST)
    @ResponseBody
    public Model userSignUpApi(
        @Valid @ModelAttribute("user") User user, BindingResult formResult,Model ctx){
            user.setTheUsername();
            if (formResult.hasErrors()) {
                ctx.addAttribute("userMessages", formResult.getFieldErrors());
                ctx.addAttribute("template", "sign-up.html");
            } else {
                User newUser = usersDB.register(user);
                ctx.addAttribute("user", newUser);
            }
            return ctx;
    }
    
    
}