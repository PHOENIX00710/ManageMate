package com.example.ManageMate.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
