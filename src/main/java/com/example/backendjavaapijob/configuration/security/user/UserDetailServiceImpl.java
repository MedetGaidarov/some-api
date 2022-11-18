package com.example.backendjavaapijob.configuration.security.user;

import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            Optional<User> user = userService.findByUsername(username);

            if (user.isPresent())
                return UserDetailsImpl.build(user.get());

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
