package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.Posting.PostingRequest;
import com.example.ManageMate.DTO.Posting.PostingResponse;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Services.PostingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
public class PostingController {

    private final PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<PostingResponse>> getAllJobPosts(){
        return postingService.getAllJobs();
    }

    @PostMapping("/create-new-job")
    public ResponseEntity<Response> createNewJob(@RequestBody PostingRequest posting){
        return postingService.postNewJob(posting);
    }
}
