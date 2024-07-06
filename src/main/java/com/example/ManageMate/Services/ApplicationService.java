package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.Application.ApplicationRequest;
import com.example.ManageMate.DTO.Response;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {
    ResponseEntity<Response> submitApplication(ApplicationRequest applicationRequest, Long userId, Long postId);
    ResponseEntity<Response> getAllApplications();
}
