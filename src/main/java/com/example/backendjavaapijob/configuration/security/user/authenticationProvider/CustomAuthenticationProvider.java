package com.example.backendjavaapijob.configuration.security.user.authenticationProvider;

import com.example.backendjavaapijob.configuration.security.user.UserDetailsImpl;
import com.example.backendjavaapijob.domain.user.model.User;
import com.example.backendjavaapijob.domain.user.model.UserType;
import com.example.backendjavaapijob.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class CustomAuthenticationProvider implements AuthenticationProvider {



    private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String userType = authentication.getName();
//
//        if(!(authentication.getCredentials() instanceof User))
//        {
//            throw new IllegalArgumentException("Authentication error!");
//        }
//        User user = (User) authentication.getCredentials();
//        if(Arrays.stream(UserType.values()).noneMatch(p -> p.equalsIgnoreCase(userType))))
//        {
//            throw new IllegalArgumentException("Wrong user type!");
//        }
//
//        logger.info("CustomAuthenticationProvider: profileType {}. Credentials: {}",
//                user, user.getEmail()
//        );
//
//        User fetchedUser = userRepository.findByEmail(user.getEmail()).get();
//
//        logger.info("CustomAuthenticationProvider: fetched user {}", user.getEmail());
//
//
//    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

}
