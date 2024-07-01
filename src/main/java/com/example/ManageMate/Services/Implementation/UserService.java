package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.User;
import com.example.ManageMate.Repositories.UserRepository;
import com.example.ManageMate.Services.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    //Signup
    public String registerUser(RegisterUser registerUser){

        User newUser=modelMapper.map(registerUser,User.class);


        if(userRepo.existsByEmail(newUser.getEmail())){
            throw new CustomError("Email Already Exists","USER_EXISTS");
        }

        userRepo.save(newUser);
        return "User Registered Successfully";

    }

    public UserSession loginUser(LoginDetails loginDetails){

        User currUser;
        UserSession userSession=null;

        currUser=userRepo.findByEmail(loginDetails.getEmail());
        if(currUser == null){
            throw new CustomError("User Not Found","404");
        }

        else {
            userSession = this.modelMapper.map(currUser, UserSession.class);
            return userSession;
        }

    }

    // Get all Users
    public ArrayList<User> getAllUsers(){
        return new ArrayList<>(userRepo.findAll());
    }
}
