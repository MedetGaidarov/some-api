package com.example.backendjavaapijob.domain.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserType {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";


    private String userType;

    public static String[] values() {
        return new String[]{USER, ADMIN};
    }

    public static String userRole() {
        return "ROLE_" + USER;
    }

    public static String adminRole() {
        return "ROLE_" + ADMIN;
    }
}
