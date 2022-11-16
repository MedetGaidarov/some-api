package com.example.backendjavaapijob.domain.user.service;


import com.example.backendjavaapijob.domain.user.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById();

}
