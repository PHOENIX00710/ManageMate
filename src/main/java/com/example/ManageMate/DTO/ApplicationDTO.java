package com.example.ManageMate.DTO;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {

    @Lob
    private String coverLetter;
    @Lob
    private String joiningDate;

}
