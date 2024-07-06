package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.Profile.ProfileRequest;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.Models.User.User;

import java.io.IOException;
import java.util.ArrayList;

public interface UserServiceInterface {
    AuthResponse registerUser(RegisterUser registerUser);
    AuthResponse loginUser(LoginDetails loginDetails);
    ArrayList<User> getAllUsers();
    Response updateProfile(ProfileRequest profileRequest, Long userId) throws IOException;
}
