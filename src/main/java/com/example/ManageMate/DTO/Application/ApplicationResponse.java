package com.example.ManageMate.DTO.Application;

import com.example.ManageMate.DTO.Profile.ProfileResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicationResponse {
    private ProfileResponse user;
    private String coverLetter;
    private String joiningReason;
}
