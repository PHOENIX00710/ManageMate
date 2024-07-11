package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Posting.PostingRequest;
import com.example.ManageMate.DTO.Posting.PostingResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Exceptions.NotFound;
import com.example.ManageMate.Models.Posting.Posting;
import com.example.ManageMate.Models.User.Auth.UserRole;
import com.example.ManageMate.Models.User.Role;
import com.example.ManageMate.Models.User.User;
import com.example.ManageMate.Repositories.PostingRepository;
import com.example.ManageMate.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServiceImpl implements com.example.ManageMate.Services.PostingService {


    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PostingServiceImpl(PostingRepository postingRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postingRepository = postingRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<Response> postNewJob(PostingRequest postingRequest) {
        try{
            System.out.println(postingRequest);
            Posting newJobPosting=modelMapper.map(postingRequest,Posting.class);
            postingRepository.save(newJobPosting);
            PostingResponse newPosting=modelMapper.map(newJobPosting,PostingResponse.class);
            return ResponseEntity.ok(new Response(true,"Successfully Posted!",newPosting));
        }
        catch (Exception e){
            System.out.println(e);
            throw new CustomError("Error in Job Posting","JOB_POSTING_CODE");
        }
    }

    @Override
    public ResponseEntity<List<PostingResponse>> getAllJobs() {
        List<Posting> allPostings=postingRepository.findAll();
        List<PostingResponse> responses=new ArrayList<>();
        for(Posting posting:allPostings){
            PostingResponse temp=modelMapper.map(posting,PostingResponse.class);
            responses.add(temp);
        }
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<PostingResponse>> getRecommendations(Long userId) {

        User currUser=userRepository.findById(userId).orElseThrow(
                ()->new NotFound("User Not Found","NOT_FOUND")
        );

        ArrayList<PostingResponse> recommendations=new ArrayList<>();
        List<Posting> postings=postingRepository.findAll();

        for(Posting posting:postings){

            List<String> locations=currUser.getLocations();
            List<String> locationsOffered=posting.getLocations();
            boolean matches=false;

            for(String location:locationsOffered){
                for(String offeredLocation:locationsOffered){
                    if(location.equalsIgnoreCase(offeredLocation)){
                        matches=true;
                        break;
                    }
                }
            }

            if(matches){
                matches=false;
                List<Role> roles=currUser.getPreferredRoles();
                Role rolesOffered = posting.getExperience();

                for(Role role:roles){
                    if(role.getName().equalsIgnoreCase(rolesOffered.getName())
                        && role.getRangeStart() >= rolesOffered.getRangeStart()){
                        matches=true;
                        break;
                    }
                }
            }

            if(matches) recommendations.add(modelMapper.map(posting,PostingResponse.class));
        }

        return ResponseEntity.ok(recommendations);
    }

    @Override
    public void deletePost(Long postId) {
        try{
            postingRepository.deleteById(postId);
        }
        catch (Exception e){
            throw new CustomError("Error in Deletion","INTERNAL_SERVER_ERROR");
        }
    }
}
