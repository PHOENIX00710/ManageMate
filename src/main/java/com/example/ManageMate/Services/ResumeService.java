package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Models.User.Resume;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResumeService {
    public Response saveFile(MultipartFile file) throws IOException;
    public Resume getFile(Long id);
}
