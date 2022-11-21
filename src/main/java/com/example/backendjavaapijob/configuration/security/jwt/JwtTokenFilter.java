package com.example.backendjavaapijob.configuration.security.jwt;



import com.example.backendjavaapijob.configuration.security.user.CustomUserDetailService;
import com.example.backendjavaapijob.infrastructure.utils.JwtTokenUtil;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailService customUserDetailsService;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    private static final HashSet<String> excludeUri;

    static {
        excludeUri = new HashSet<>(
                Arrays.asList(
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/csrf",
                        "/"
                )
        );
    }

    public JwtTokenFilter(JwtTokenUtil jwtTokenUtil,
                          CustomUserDetailService customUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            logger.error("Authorization header missing");
            filterChain.doFilter(request, response);
            return;
        }
        logger.info("Request `{}` goes further through JwtTokenFilter", request.getRequestURI());
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(token);
        if (userDetails == null)
            throw new ServletException("Couldn't extract user from JWT credentials");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI().substring(contextPath.length());
        logger.info("JwtTokenFilter excluded URI `{}`", uri);
        return excludeUri.contains(uri);
    }
}

