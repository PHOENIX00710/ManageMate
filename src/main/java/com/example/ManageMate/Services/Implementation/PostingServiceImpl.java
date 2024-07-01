package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.PostingDTO;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.Posting;
import com.example.ManageMate.Repositories.PostingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostingServiceImpl implements com.example.ManageMate.Services.PostingService {

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ResponseEntity<String> postNewJob(PostingDTO postingDTO) {
        try{
            Posting newJobPosting=modelMapper.map(postingDTO,Posting.class);
            postingRepository.save(newJobPosting);
            return ResponseEntity.ok("Successfully Posted");
        }
        catch (Exception e){
            throw new CustomError("Error in Job Posting","JOB_POSTING_CODE");
        }
    }
}
