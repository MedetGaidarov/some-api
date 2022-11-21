package com.example.backendjavaapijob.configuration.security.user.authenticationProvider;

import com.example.backendjavaapijob.configuration.security.user.UserDetailsImpl;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.model.UserType;
import com.example.backendjavaapijob.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userType = authentication.getName();

        if(!(authentication.getCredentials() instanceof User))
        {
            throw new IllegalArgumentException("Authentication error!");
        }
        User user = (User) authentication.getCredentials();
        if(Arrays.stream(UserType.values()).noneMatch(p -> p.equalsIgnoreCase(userType)))
        {
            throw new IllegalArgumentException("Wrong user type!");
        }
        User fetchedUser = userRepository.findByEmail(user.getEmail()).
                orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with email %s not found", user.getEmail())
                        )
                );

        if (!passwordEncoder.matches(user.getPassword(), fetchedUser.getPassword())) {
            throw new IllegalArgumentException("Password does not match");
        }
        try {
            UserDetailsImpl userDetails = UserDetailsImpl.build(user);

            return new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        } catch (Exception e) {
            logger.error("Caught login exception: {}", e.getMessage());
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
