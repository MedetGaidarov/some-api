package com.example.backendjavaapijob.ui.controller;


import com.example.backendjavaapijob.domain.user.model.UserType;
import com.example.backendjavaapijob.domain.user.service.UserService;
import com.example.backendjavaapijob.ui.dto.DefaultResponseDto;
import com.example.backendjavaapijob.ui.dto.mapper.UserMapper;
import com.example.backendjavaapijob.ui.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper;


    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        try {
            String ss = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
            System.out.println(ss);
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "User successfully created", userService.save(userMapper.toUser(userDto))));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponseDto("SUCCESS", "User successfully found", userService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new DefaultResponseDto("FAULT", e.getMessage()));
        }
    }


}

