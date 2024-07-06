package com.example.ManageMate.DTO.Profile;

import com.example.ManageMate.Models.User.Image;
import com.example.ManageMate.Models.User.Resume;
import com.example.ManageMate.Models.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    private String name;
    private String phone;
    private MultipartFile resume;
    private MultipartFile  profilePicture;
    private List<Role> preferredRoles = new ArrayList<>();
    private List<String> locations = new ArrayList<>();
}
