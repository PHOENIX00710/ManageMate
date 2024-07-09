package com.example.ManageMate.Services;

import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Models.User.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePictureService {
    public Response savePicture(MultipartFile file) throws IOException;
    public Image getPicture(Long id);
}
