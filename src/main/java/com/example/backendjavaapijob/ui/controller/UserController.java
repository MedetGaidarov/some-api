package com.example.backendjavaapijob.ui.controller;


import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.service.UserService;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "User successfully found", userService.getUserById(id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }


}

