package com.example.ManageMate.DTO.Application;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @Lob
    private String coverLetter;
    @Lob
    private String joiningReason;

}
