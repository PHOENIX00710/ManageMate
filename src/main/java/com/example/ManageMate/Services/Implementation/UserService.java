package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.DTO.User.UserSession;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.User;
import com.example.ManageMate.Models.UserRole;
import com.example.ManageMate.Repositories.UserRepository;
import com.example.ManageMate.Services.UserServiceInterface;
import com.example.ManageMate.auth.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserServiceInterface {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    //Signup
    public AuthResponse registerUser(RegisterUser registerUser){

        var user = User.builder()
                .name(registerUser.getName())
                .email(registerUser.getEmail())
                .phone(registerUser.getPhone())
                .password(passwordEncoder.encode(registerUser.getPassword()))
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        var accessToken = jwtService.generateToken(savedUser);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .build();

    }

    public AuthResponse loginUser(LoginDetails loginDetails){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDetails.getEmail(),
                        loginDetails.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginDetails.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found!"));
        var accessToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .build();

    }

    // Get all Users
    public ArrayList<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }
}
