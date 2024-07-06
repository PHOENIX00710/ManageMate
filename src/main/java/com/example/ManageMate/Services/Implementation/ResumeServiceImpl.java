package com.example.ManageMate.Services.Implementation;

import com.example.ManageMate.DTO.Response;
import com.example.ManageMate.Exceptions.CustomError;
import com.example.ManageMate.Models.User.Resume;
import com.example.ManageMate.Repositories.ResumeFileRepository;
import com.example.ManageMate.Services.ResumeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeFileRepository fileRepository;

    public ResumeServiceImpl(ResumeFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Response saveFile(MultipartFile file) throws IOException {
        Resume pdfFile = new Resume();
        try{
            pdfFile.setFileName(file.getOriginalFilename());
            pdfFile.setData(file.getBytes());
            fileRepository.save(pdfFile);
        }
        catch (Exception e){
            throw new CustomError("Error in saving File","INTERNAL_SERVER_ERROR");
        }
        return new Response(true,"Resume Saved Successfully",pdfFile);
    }

    @Override
    public Resume getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(
                ()->new CustomError("File not Found","NOT_FOUND")
        );
    }
}
