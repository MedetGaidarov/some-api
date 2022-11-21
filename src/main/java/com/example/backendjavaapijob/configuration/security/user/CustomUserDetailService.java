package com.example.backendjavaapijob.configuration.security.user;

import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.service.UserService;
import com.example.backendjavaapijob.infrastructure.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



public class CustomUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (token == null || token.isEmpty()) throw new IllegalArgumentException("Token cannot be null or empty");
        try {
            final String email = jwtTokenUtil.getEmail(token);
            final String role = jwtTokenUtil.getRole(token);
            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", token)));
            UserDetailsImpl userDetails = UserDetailsImpl.build(user);
            logger.info(String.format("Extracted Profile: %s", userDetails));

            return userDetails;
        } catch (Exception e) {
            logger.error("Wasn't able to load user `{}`. Exception occurred `{}`", token, e.getMessage());
            return null;
        }
    }
}
