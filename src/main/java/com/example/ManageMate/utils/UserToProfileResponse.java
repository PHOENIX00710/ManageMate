package com.example.ManageMate.utils;

import com.example.ManageMate.DTO.Profile.ProfileResponse;
import com.example.ManageMate.Models.User.User;
import org.springframework.stereotype.Component;

@Component
public class UserToProfileResponse {

        public ProfileResponse convertToProfile(User currUser){

            // Build ProfileResponse DTO from updated User entity
            ProfileResponse profileResponse = ProfileResponse.builder()
                    .name(currUser.getName())
                    .email(currUser.getEmail())
                    .phone(currUser.getPhone())
                    .locations(currUser.getLocations())
                    .preferredRoles(currUser.getPreferredRoles())
                    .build();

            // Set additional fields like resumeFile and profilePicture
            if (currUser.getResumeFile() != null) {
                profileResponse.setResumeFile(currUser.getResumeFile().getData());
            }
            if (currUser.getImageFile() != null) {
                profileResponse.setProfilePicture(currUser.getImageFile().getData());
            }
            return profileResponse;

        }
}
