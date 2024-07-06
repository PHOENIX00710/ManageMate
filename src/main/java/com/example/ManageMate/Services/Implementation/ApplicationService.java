package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Application.ApplicationRequest;
import com.example.ManageMate.DTO.Application.ApplicationResponse;
import com.example.ManageMate.DTO.Profile.ProfileResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.Application.Application;
import com.example.ManageMate.Models.Posting.Posting;
import com.example.ManageMate.Models.User.User;
import com.example.ManageMate.Repositories.ApplicationRepository;
import com.example.ManageMate.Repositories.PostingRepository;
import com.example.ManageMate.Repositories.UserRepository;
import com.example.ManageMate.utils.UserToProfileResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements com.example.ManageMate.Services.ApplicationService {

    final private ApplicationRepository applicationRepository;
    final private UserRepository userRepository;
    final private PostingRepository postingRepository;
    final private ModelMapper modelMapper;
    final private UserToProfileResponse toProfileResponse;

    public ApplicationService(ApplicationRepository applicationRepository, UserRepository userRepository, PostingRepository postingRepository, ModelMapper modelMapper, UserToProfileResponse profileResponseMaker, UserToProfileResponse toProfileResponse) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.postingRepository = postingRepository;
        this.modelMapper = modelMapper;
        this.toProfileResponse = toProfileResponse;
    }

    @Override
    public ResponseEntity<Response> submitApplication(ApplicationRequest applicationRequest,
                                                      Long userId, Long postId) {
        // Check if the user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw  new CustomError("User not found", "NOT_FOUND_404");
        }

        // Check if the posting exists
        Optional<Posting> postingOptional = postingRepository.findById(postId);
        if (postingOptional.isEmpty()) {
            throw new CustomError("Posting not found","NOT_FOUND_404");
        }

        User currUser = userOptional.get();
        Posting currPosting = postingOptional.get();

        try {
            // Map DTO to
            Application application = modelMapper.map(applicationRequest, Application.class);
            application.setUser(currUser);
            application.setPosting(currPosting);

            applicationRepository.save(application);

            ProfileResponse profileResponse=toProfileResponse.convertToProfile(currUser);

            ApplicationResponse newApplication =
                    ApplicationResponse.builder()
                            .user(profileResponse)
                            .joiningReason(application.getJoiningReason())
                            .coverLetter(application.getCoverLetter())
                            .build();

            return ResponseEntity.ok(new Response(true,"Application Submitted Successfully!!",newApplication));
        }
        catch (Exception e) {
            throw new CustomError("Issue in Application Submission", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public ResponseEntity<Response> getAllApplications() {

        List<Application> applications=applicationRepository.findAll();
        if(applications.isEmpty())
            throw new CustomError("No applications available","RESOURCE_NOT_FOUND");

        List<ApplicationResponse> responses= new ArrayList<>();

        for(Application application:applications){

            User currUser=application.getUser();
            ProfileResponse profileResponse=toProfileResponse.convertToProfile(currUser);

            ApplicationResponse temp =
                    ApplicationResponse.builder()
                            .user(profileResponse)
                            .joiningReason(application.getJoiningReason())
                            .coverLetter(application.getCoverLetter())
                            .build();

            responses.add(temp);
        }

        return ResponseEntity.ok(new Response(true,"Here are all the open applications",responses));
    }
}
