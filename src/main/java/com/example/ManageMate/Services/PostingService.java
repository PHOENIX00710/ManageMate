package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.Posting.PostingRequest;
import com.example.ManageMate.DTO.Posting.PostingResponse;
import com.example.ManageMate.DTO.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostingService {
    ResponseEntity<Response> postNewJob(PostingRequest postingRequest);
    ResponseEntity<List<PostingResponse>> getAllJobs();
    void deletePost(Long postId);
}
