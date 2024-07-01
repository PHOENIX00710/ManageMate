package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.ApplicationDTO;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.Application;
import com.example.ManageMate.Models.Posting;
import com.example.ManageMate.Models.User;
import com.example.ManageMate.Repositories.ApplicationRepository;
import com.example.ManageMate.Repositories.PostingRepository;
import com.example.ManageMate.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService implements com.example.ManageMate.Services.ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> submitApplication(ApplicationDTO applicationDTO, Long userId, Long postId) {
        try {
            // Check if the user exists
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Check if the posting exists
            Optional<Posting> postingOptional = postingRepository.findById(postId);
            if (postingOptional.isEmpty()) {
                return new ResponseEntity<>("Posting not found", HttpStatus.NOT_FOUND);
            }

            User currUser = userOptional.get();
            Posting currPosting = postingOptional.get();

            // Map DTO to Entity
            Application application = modelMapper.map(applicationDTO, Application.class);
            application.setUser(currUser);
            application.setPosting(currPosting);

            // Save application
            applicationRepository.save(application);

            return new ResponseEntity<>("Application submitted successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomError("Issue in Application Submission", "INTERNAL_SERVER_ERROR");
        }
    }
}
