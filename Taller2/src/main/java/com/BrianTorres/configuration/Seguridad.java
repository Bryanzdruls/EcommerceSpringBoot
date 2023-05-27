package com.BrianTorres.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Seguridad implements WebMvcConfigurer{
    
    @Autowired
    private AutenticacionExitosa aut;
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/images/**","/cliente/ingresar","/css/**","/cliente/guardar","/cliente/registro", "/cliente/login").permitAll()
                .anyRequest()
                .authenticated().and().formLogin(form -> form
                .loginPage("/cliente/login")
                    .permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(aut)
                    .permitAll())
                    .logout(logout -> logout.logoutSuccessUrl("/cliente/login").permitAll())
                    .build();
                }   

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    public void addResourceHandler(ResourceHandlerRegistry registry){
        String path ="file:///C:/Users/eltio/OneDrive/Escritorio/taller2Final/Taller2/src/main/resources/static/images/";
        registry.addResourceHandler("/images/**").addResourceLocations(path);
    }
    
}
