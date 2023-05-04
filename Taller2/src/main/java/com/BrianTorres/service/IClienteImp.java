package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.IClienteRepo;
import com.BrianTorres.model.Cliente;

@Service
public class IClienteImp implements IClienteService {
    @Autowired
    private IClienteRepo clienteRepo;

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
        return clienteRepo.save(cliente);
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return clienteRepo.findByEmail(email);
    }
    
}
