package com.example.ManageMate.Models.Posting;

import com.example.ManageMate.Models.User.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicants_no")
    private Long numberOfApplicants;

    @Lob
    private String description;

    @Lob
    private String responsibilities;

    @ElementCollection
    @CollectionTable(name = "posting_locations", joinColumns = @JoinColumn(name = "posting_id"))
    @Column(name = "location")
    private List<String> locations = new ArrayList<>();

    private String companyName;

    @Embedded
    private Role experience;

    @Column(name = "createdAt")
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(name = "modifiedAt")
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate modifiedAt;

    @PrePersist
    public void prePersist() {
        if (this.numberOfApplicants == null) {
            this.numberOfApplicants = 0L; // Default value set to 0
        }
    }
}
