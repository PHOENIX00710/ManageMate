package com.example.ManageMate.Models.Application;

import com.example.ManageMate.DTO.Profile.ProfileResponse;
import com.example.ManageMate.Models.Posting.Posting;
import com.example.ManageMate.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @Lob
    private String coverLetter;

    @Lob
    private String joiningReason;

}
