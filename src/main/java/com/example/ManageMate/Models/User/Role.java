package com.example.ManageMate.Models.User;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String name;
    private int rangeStart;
    private int rangeEnd;
}
