package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.Profile.ProfileRequest;
import com.example.ManageMate.DTO.Profile.ProfileResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.Models.User.User;
import com.example.ManageMate.Services.UserServiceInterface;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping(path = "/updateProfile/{userId}")
    public ResponseEntity<ProfileResponse> updateProfile(@ModelAttribute ProfileRequest profileRequest,
                                                  @PathVariable Long userId) throws IOException {
        ProfileResponse profileResponse = (ProfileResponse) userService.updateProfile(profileRequest,userId).getObj();

        // Optionally set headers if needed, e.g., content type for BLOB data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF); // Set content type as per your data type

        // Set appropriate filename for download if needed
        headers.setContentDispositionFormData("inline", "resume.pdf");

        return new ResponseEntity<>(profileResponse, headers, HttpStatus.OK);

    }


}
