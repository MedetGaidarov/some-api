package com.example.backendjavaapijob.configuration.security;

import com.example.backendjavaapijob.configuration.security.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;


    // here authentication provider



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagger-ui.html",
                "/privacy-policy",
                "/h2-console/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();


        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/**").permitAll()
//                .antMatchers("/beats/encrypted").permitAll()
                .antMatchers("/demo/users/**").permitAll()
                .anyRequest().authenticated();

//        http.addFilterBefore(,UsernamePasswordAuthenticationFilter.class)

    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws  Exception
    {
        return super.authenticationManagerBean();
    }
}
