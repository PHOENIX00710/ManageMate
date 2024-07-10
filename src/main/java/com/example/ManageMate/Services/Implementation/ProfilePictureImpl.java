package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Exceptions.NotFound;
import com.example.ManageMate.Models.User.Image;
import com.example.ManageMate.Repositories.ProfilePictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePictureImpl implements com.example.ManageMate.Services.ProfilePictureService {

    private final ProfilePictureRepository profilePictureRepository;

    public ProfilePictureImpl(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }


    @Override
    public Response savePicture(MultipartFile file) throws IOException {
        Image profilePicture = new Image();
        try{
            profilePicture.setFileName(file.getOriginalFilename());
            profilePicture.setData(file.getBytes());
            profilePictureRepository.save(profilePicture);
        }
        catch (Exception e){
            throw new CustomError("Error in saving Photo","INTERNAL_SERVER_ERROR");
        }
        return new Response(true,"Photo Saved Successfully",profilePicture);
    }

    @Override
    public Image getPicture(Long id) {
        return profilePictureRepository.findById(id).orElseThrow(
                ()->new NotFound("Photo not Found","NOT_FOUND")
        );
    }
}
