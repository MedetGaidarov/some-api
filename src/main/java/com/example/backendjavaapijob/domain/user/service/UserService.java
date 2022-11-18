package com.example.backendjavaapijob.domain.user.service;


import com.example.backendjavaapijob.domain.user.model.User;
import javassist.NotFoundException;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface UserService {
    User save(User user);
    Optional<User> findById(Long id) throws NotFoundException;
    Optional<User> findByUsername(String username) throws NotFoundException;
}
