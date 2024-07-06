package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Posting.PostingRequest;
import com.example.ManageMate.DTO.Posting.PostingResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.Posting.Posting;
import com.example.ManageMate.Repositories.PostingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServiceImpl implements com.example.ManageMate.Services.PostingService {

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ResponseEntity<Response> postNewJob(PostingRequest postingRequest) {
        try{
            Posting newJobPosting=modelMapper.map(postingRequest,Posting.class);
            postingRepository.save(newJobPosting);
            PostingResponse newPosting=modelMapper.map(newJobPosting,PostingResponse.class);
            return ResponseEntity.ok(new Response(true,"Successfully Posted!",newPosting));
        }
        catch (Exception e){
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
}
