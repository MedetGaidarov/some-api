package com.example.backendjavaapijob.ui.controller.auth;


import com.example.backendjavaapijob.configuration.security.user.UserDetailsImpl;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.model.UserType;
import com.example.backendjavaapijob.ui.dto.auth.AuthRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<Object> authenticate(@RequestHeader("x-profile-type") String type, @RequestBody AuthRequestDto authRequestDto)
    {
        try
        {
            final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

            authorities.add(new SimpleGrantedAuthority(UserType.userRole()));

            logger.info("User type {}, username {}",
                    type, authRequestDto.getUsername() );
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), new User(authRequestDto.getUsername()), authorities));
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

            logger.info(principal.toString());

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION).body("Registered");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
