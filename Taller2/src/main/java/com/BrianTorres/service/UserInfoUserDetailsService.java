/*package com.BrianTorres.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.BrianTorres.dao.IClienteRepo;
import com.BrianTorres.model.Cliente;




@Component
public class UserInfoUserDetailsService implements UserDetailsService{
    @Autowired
    private IClienteRepo repo; 

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente=repo.findByEmail(email);
        return cliente.map(UserInfoUserDetails::new)
            .orElseThrow(()-> new UsernameNotFoundException("user not found "+ email));
    }




}*/
