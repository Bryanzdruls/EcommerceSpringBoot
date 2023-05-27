package com.BrianTorres.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.IClienteRepo;
import com.BrianTorres.model.Cliente;

@Service
public class IClienteImp implements IClienteService {
    @Autowired
    private IClienteRepo clienteRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Cliente> findAll() {
       return clienteRepo.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepo.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setPass(encoder.encode(cliente.getPass()));
        return clienteRepo.save(cliente);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return clienteRepo.findByEmail(email);
    }



    @Override
    public void encriptar() {
        Cliente admin=clienteRepo.findById(Long.parseLong("1")).get();
        if (!admin.getPass().contains("$")) {
            admin.setPass(encoder.encode(admin.getPass()));
        }
        clienteRepo.save(admin);
    }

    @Override
    public void insertarAdmin() {
        Cliente Admin = new Cliente(Long.parseLong("1"), "Admin", "123", "mello@gmail.com", "ADMIN", "EMMANUEL", "HERNANDEZ", new Date(), "evg", "1020", null, null);
        clienteRepo.save(Admin);
    }
    
}
