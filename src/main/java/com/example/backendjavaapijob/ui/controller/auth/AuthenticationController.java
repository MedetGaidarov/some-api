package com.example.backendjavaapijob.ui.controller.auth;


import com.example.backendjavaapijob.configuration.security.user.UserDetailsImpl;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.infrastructure.utils.JwtTokenUtil;
import com.example.backendjavaapijob.ui.dto.auth.AuthRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<Object> authenticate(@RequestHeader("x-profile-type") String type,
                                               @RequestBody AuthRequestDto authRequestDto) {
        try {
            logger.info("Profile type {}, email {}, password {}",
                    type, authRequestDto.getEmail(), authRequestDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            type,
                            new User(authRequestDto.getEmail(), authRequestDto.getPassword())
                    )
            );
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();


            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(principal)
                    )
                    .body(principal.toString());
        } catch (AuthenticationException ex) {
            logger.error("Authentication exception: " + ex.getMessage());
            ;
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception e) {
            logger.error("Error in LOGIN: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
