package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Models.User;
import com.example.ManageMate.Services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping(path = "/signup")
    public String signupHelper(@RequestBody RegisterUser registerUser) {
        return userService.registerUser(registerUser);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserSession> loginHelper(@RequestBody LoginDetails loginDetails){
        UserSession currUserSession= userService.loginUser(loginDetails);
        return new ResponseEntity<>(currUserSession, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUsers")
    public ArrayList<User> getAUsers(){
        return userService.getAllUsers();
    }

}
