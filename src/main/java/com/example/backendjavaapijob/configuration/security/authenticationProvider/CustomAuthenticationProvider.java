package com.example.backendjavaapijob.configuration.security.authenticationProvider;

import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String profileType = authentication.getName();
        if (!(authentication.getCredentials() instanceof User))
            throw new IllegalArgumentException("Authentication error!");
        User user = (User) authentication.getCredentials();
        logger.info("CustomAuthenticationProvider: profileType {}. Credentials: {}, {}",
                profileType, user.getEmail(), user.getPassword()
        );

        User fetchedUser = userRepository.findByUsername(user.getUsername()).
                orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with email %s not found", user.getUsername())
                        )
                );

        Boolean bo = passwordEncoder.matches("Areke123", fetchedUser.getPassword());


        if (!passwordEncoder.matches(user.getPassword(), fetchedUser.getPassword())) {
            throw new IllegalArgumentException("Password does not match");
        }
        logger.info("CustomAuthenticationProvider: fetched user {}", user.getUsername());
        try {
            String principal = (String) authentication.getPrincipal();

            return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), authentication.getAuthorities());
        } catch (BadCredentialsException e) {
            logger.error("Caught login exception: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
