package com.example.ManageMate.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private Long id;
    private String email;
    private String name;
    private String phone;
}
