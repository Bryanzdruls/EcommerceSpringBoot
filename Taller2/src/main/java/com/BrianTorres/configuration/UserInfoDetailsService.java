package com.BrianTorres.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.BrianTorres.dao.IClienteRepo;
import com.BrianTorres.model.Cliente;

@Component
public class UserInfoDetailsService implements UserDetailsService{

    @Autowired
    private IClienteRepo repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Cliente> cliente =repo.findByEmail(username);
       return cliente.map(UserInfoDetails::new)
       .orElseThrow(()-> new UsernameNotFoundException("user not found "+ username));
    }


    
}
