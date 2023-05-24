package com.BrianTorres.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.BrianTorres.dao.IClienteRepo;
import com.BrianTorres.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacionExitosa implements AuthenticationSuccessHandler {
    @Autowired
    private IClienteRepo iClienteRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        String rol = authentication.getAuthorities().toString();

        UserDetails user = (UserDetails) authentication.getPrincipal();
        Cliente cliente=iClienteRepo.findByEmail(user.getUsername()).get();

        request.getSession().setAttribute("idcliente", cliente.getId());
        
        if (rol.equalsIgnoreCase("[ADMIN]")) {
            response.sendRedirect("/administrador/home");
        }else {
            response.sendRedirect("/");
        }  
    }
}
