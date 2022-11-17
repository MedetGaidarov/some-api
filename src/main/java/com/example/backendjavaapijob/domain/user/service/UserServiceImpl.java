package com.example.backendjavaapijob.domain.user.service;

import com.example.backendjavaapijob.domain.user.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setUsername("mgaidarov");
        user.setEmail("mgaiidarov@gmail.com");
        user.setId(1L);
        return user;
    }
}
