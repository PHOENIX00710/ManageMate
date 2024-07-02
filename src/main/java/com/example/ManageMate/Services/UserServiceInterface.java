package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Models.User;

import java.util.ArrayList;

public interface UserServiceInterface {
    AuthResponse registerUser(RegisterUser registerUser);
    AuthResponse loginUser(LoginDetails loginDetails);
    ArrayList<User> getAllUsers();
}
