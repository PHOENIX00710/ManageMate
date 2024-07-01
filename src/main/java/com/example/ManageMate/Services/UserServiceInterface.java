package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Models.User;

import java.util.ArrayList;

public interface UserServiceInterface {
    String registerUser(RegisterUser registerUser);
    UserSession loginUser(LoginDetails loginDetails);
    ArrayList<User> getAllUsers();
}
