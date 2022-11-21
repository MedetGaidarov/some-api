package com.example.backendjavaapijob.ui.dto.user;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    String email;
    String username;
    String password;
}
