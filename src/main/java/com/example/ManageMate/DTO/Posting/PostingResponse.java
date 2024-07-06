package com.example.ManageMate.DTO.Posting;

import com.example.ManageMate.Models.User.Role;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostingResponse {

    @Lob
    private String description;
    @Lob
    private String responsibilities;

    private Long numberOfApplicants;
    private List<String> locations = new ArrayList<>();
    private String companyName;
    private Role experience;
}
