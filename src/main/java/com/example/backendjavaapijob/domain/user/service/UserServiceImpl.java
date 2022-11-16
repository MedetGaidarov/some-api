package com.example.backendjavaapijob.domain.user.service;

import com.example.backendjavaapijob.domain.user.model.User;



public class UserServiceImpl implements UserService{
    @Override
    public User getUserById() {
        User user = new User();
        user.setUsername("mgaidarov");
        user.setEmail("mgaiidarov@gmail.com");
        user.setId(1111L);
        return user;
    }
}
