package com.springapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springapi.models.User;
import com.springapi.repositories.mongo.UserRepo;

/**
 * UserService
 */
@Service
public class UsersService {

    @Autowired
    UserRepo usersDB;

    public List<User> getDBUsers() {
        return usersDB.findAll();
    }

    public User register(User newuser) {
        newuser.setTheUsername();
        String pw_hash = BCrypt.hashpw(newuser.getPassword(), BCrypt.gensalt(10));
        newuser.setPassword(pw_hash);
        return usersDB.save(newuser);
    }

    public User getUser(String email) {
        return usersDB.findByEmail(email);
    }

    public Boolean userExist(String email){
        Boolean result = getUser(email) != null ? true : false;
        return result;
    }

    public User authUser(Map<String,String> user){
        User targetUser = getUser(user.get("email") );
        if( targetUser != null && BCrypt.checkpw( user.get("password"), targetUser.getPassword() ))
        {
            return targetUser;
        }
        return new User();
    }
}