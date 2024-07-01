package com.example.ManageMate.DTO;

import com.example.ManageMate.Models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostingDTO {

        @Lob
        private String description;
        @Lob
        private String responsibilities;

        private List<String> locations = new ArrayList<>();
        private String companyName;
        private Role experience;
}
