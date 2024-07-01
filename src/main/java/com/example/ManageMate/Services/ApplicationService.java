package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.ApplicationDTO;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {
    ResponseEntity<String> submitApplication(ApplicationDTO applicationDTO, Long userId, Long postId);
}
