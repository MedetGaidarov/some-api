package com.example.backendjavaapijob.domain.user.service;

import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (user == null) throw new IllegalArgumentException("User can't be null");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findById(Long id) throws NotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("User with id %s not found", id));
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) throws NotFoundException {


        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException(String.format("User with username %s not found", user));
        }
        return user;
    }

}
