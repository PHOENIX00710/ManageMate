package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Models.User;
import com.example.ManageMate.Services.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/auth")
public class UserController {

    private final UserServiceInterface userService;

    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponse> signupHelper(@RequestBody RegisterUser registerUser) {
        return new ResponseEntity<>(userService.registerUser(registerUser),HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> loginHelper(@RequestBody LoginDetails loginDetails){
        AuthResponse currUserSession= userService.loginUser(loginDetails);
        return new ResponseEntity<>(currUserSession, HttpStatus.OK);
    }

    
    @GetMapping(path = "/getAllUsers")
    public ArrayList<User> getAUsers(){
        return userService.getAllUsers();
    }

}
