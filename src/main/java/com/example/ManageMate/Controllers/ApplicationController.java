package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.Application.ApplicationRequest;
import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/application")
public class ApplicationController {


    final private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping(path = "create-new-application/{userId}/{postId}")
    public ResponseEntity<Response> submitApplication(@RequestBody ApplicationRequest applicationRequest,
                                                      @PathVariable Long userId,
                                                      @PathVariable Long postId){
        return applicationService.submitApplication(applicationRequest,userId,postId);
    }

    @GetMapping(path = "/getAllApplications")
    public ResponseEntity<Response> getAllApplications(){
        System.out.println("Hello i am hereee in the controller!!");
        return applicationService.getAllApplications();
    }
}
