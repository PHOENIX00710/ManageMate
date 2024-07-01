package com.example.ManageMate.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
    private String name;
    private String email;
    private String password;
    private String phone;
}
