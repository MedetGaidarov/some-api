package com.example.backendjavaapijob.ui.dto.auth;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthRequestDto {
    String email;
    String username;
    String password;
}
