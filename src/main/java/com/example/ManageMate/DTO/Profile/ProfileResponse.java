package com.example.ManageMate.DTO.Profile;

import com.example.ManageMate.Models.User.Image;
import com.example.ManageMate.Models.User.Resume;
import com.example.ManageMate.Models.User.Role;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    @Lob
    private byte[] resumeFile;
    @Lob
    private byte[] profilePicture;

    private String name;
    private String email;
    private String phone;

    private List<Role> preferredRoles = new ArrayList<>();
    private List<String> locations = new ArrayList<>();

}
