/*package com.BrianTorres.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.BrianTorres.service.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Seguridad{
    
    @Autowired
    //private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().and()
        .formLogin(form -> form
                .loginPage("/cliente/login")
                .permitAll()
            );
        return http.build();
    }
}*/
