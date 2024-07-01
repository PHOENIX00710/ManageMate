package com.example.ManageMate.Controllers;

import com.example.ManageMate.DTO.ApplicationDTO;
import com.example.ManageMate.Services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping(path = "/{userId}/{postId}")
    public ResponseEntity<String> submitApplication(@RequestBody ApplicationDTO applicationDTO,
                                               @RequestParam Long userId,
                                               @RequestParam Long postId){
        return applicationService.submitApplication(applicationDTO,userId,postId);
    }
}
