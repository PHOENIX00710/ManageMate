package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Profile.ProfileRequest;
import com.example.ManageMate.DTO.Profile.ProfileResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.DTO.User.AuthResponse;
import com.example.ManageMate.DTO.User.LoginDetails;
import com.example.ManageMate.DTO.User.RegisterUser;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.User.Image;
import com.example.ManageMate.Models.User.Resume;
import com.example.ManageMate.Models.User.User;
import com.example.ManageMate.Models.User.Auth.UserRole;
import com.example.ManageMate.Repositories.UserRepository;
import com.example.ManageMate.Services.ProfilePictureService;
import com.example.ManageMate.Services.ResumeService;
import com.example.ManageMate.Services.UserServiceInterface;
import com.example.ManageMate.auth.service.JwtService;
import com.example.ManageMate.utils.UserToProfileResponse;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UserService implements UserServiceInterface {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ProfilePictureService profilePictureService;
    final private UserToProfileResponse toProfileResponse;
    private final ResumeService resumeService;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager, ModelMapper modelMapper, ProfilePictureService profilePictureService, UserToProfileResponse toProfileResponse, ResumeService resumeService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.profilePictureService = profilePictureService;
        this.toProfileResponse = toProfileResponse;
        this.resumeService = resumeService;
    }

    //Signup
    @Override
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

    //Login
    @Override
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
//    @PreAuthorize("has('ADMIN')")
    @Override
    public ArrayList<Response> getAllUsers(){
        System.out.println("Hello i am in service layer");
        try{
            ArrayList<User> users= new ArrayList<>(userRepository.findAll());
            ArrayList<Response> responses=new ArrayList<>();
            for(User currUser:users){
                ProfileResponse profileResponse=toProfileResponse.convertToProfile(currUser);
                responses.add(new Response(true,"User details: "+users.indexOf(currUser),profileResponse));
            }
            return responses;
        }
        catch (Exception e){
            throw new CustomError("Profile details couldn't be fetched","INTERNAL_SERVER_ERROR");
        }
    }


    @Override
    public Response updateProfile(ProfileRequest profileRequest, Long userId) throws IOException {

        if(profileRequest.getPhone() == null || profileRequest.getPhone().isEmpty()){
            throw new CustomError("Phone Number field cannot be empty","INPUT_ERROR");
        }
        if(profileRequest.getName() == null || profileRequest.getName().isEmpty()){
            throw new CustomError("Name field cannot be empty","INPUT_ERROR");
        }

        User currUser=userRepository.findById(userId).orElseThrow(
                ()->new CustomError("User Not Found","NOT_FOUND")
        );

        currUser.setName(profileRequest.getName());
        currUser.setPhone(profileRequest.getPhone());

        // Additional fields, if they are part of the request and user model
        if (profileRequest.getResume() != null && !profileRequest.getResume().isEmpty()) {
            Response response=resumeService.saveFile(profileRequest.getResume());
            currUser.setResumeFile((Resume) response.getObj());
        }
        if (profileRequest.getProfilePicture() != null && !profileRequest.getProfilePicture().isEmpty()) {
            Response profilePicture=profilePictureService.savePicture(profileRequest.getProfilePicture());
            currUser.setImageFile((Image) profilePicture.getObj());
        }
        if (profileRequest.getLocations() != null) {
            currUser.setLocations(profileRequest.getLocations());
        }
        if (profileRequest.getPreferredRoles() != null) {
            currUser.setPreferredRoles(profileRequest.getPreferredRoles());
        }

        try{
            userRepository.save(currUser);

            ProfileResponse profileResponse=toProfileResponse.convertToProfile(currUser);

            return new Response(true, "Profile Successfully Updated", profileResponse);
        }
        catch (Exception e){
            throw new CustomError("Error in saving updates","INTERNAL_SERVER_ERROR");
        }

    }

    @Override
    public ProfileResponse getProfileById(Long Id) {
        User currUser=userRepository.findById(Id).orElseThrow(
                ()->new CustomError("User Not Found","NOT_FOUND")
        );

        return toProfileResponse.convertToProfile(currUser);
    }
}
