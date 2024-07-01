package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.PostingDTO;
import org.springframework.http.ResponseEntity;

public interface PostingService {
    ResponseEntity<String> postNewJob(PostingDTO postingDTO);
}
