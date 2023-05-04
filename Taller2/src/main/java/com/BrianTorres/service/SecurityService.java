/*package com.BrianTorres.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityService {
    

    //authentication
    public UserDetailsService userDetailsService(){
        /*UserDetails admin = User.withUsername("brian")
                                .password(encoder.encode("123"))
                                .roles("ADMIN")
                                .build();
        UserDetails user = User.withUsername("juan")
                                .password(encoder.encode("123"))
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(admin,user);
        return new UserInfoUserDetailsService();
    }
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeHttpRequests()
        .requestMatchers("/administrador/**","cliente/registro","/h2-console/**").permitAll()
        .requestMatchers("/public/**","/css/**","/js/**","/images/**").permitAll()
        .requestMatchers("/login").permitAll()
        .and()
        .formLogin(login -> login
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/cliente/ingresar")
        .permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
        .csrf().ignoringRequestMatchers("/h2-console/**")
        .and()
        .headers().frameOptions().sameOrigin()
        .and().build();

	}
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    } 

}*/
