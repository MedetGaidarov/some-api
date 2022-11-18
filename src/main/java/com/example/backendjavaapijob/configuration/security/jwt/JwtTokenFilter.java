package com.example.backendjavaapijob.configuration.security.jwt;

import com.example.backendjavaapijob.configuration.security.user.UserDetailServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    @Autowired
    private  UserDetailServiceImpl userDetailService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       final String header= request.getHeader(HttpHeaders.AUTHORIZATION);
       if(header == null || !header.startsWith("Bearer "))
       {
           filterChain.doFilter(request, response);
           return;
       }

       final String token = header.split(" ")[1].trim();

        UserDetails userDetails = userDetailService.loadUserByUsername(token);
    }
}
