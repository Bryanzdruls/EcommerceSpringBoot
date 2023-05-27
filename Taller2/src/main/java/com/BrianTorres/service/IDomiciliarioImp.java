package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.IDomiciliarioRepo;
import com.BrianTorres.model.Domiciliario;

@Service
public class IDomiciliarioImp implements IDomiciliarioService{


    @Autowired
    private IDomiciliarioRepo domiciliarioRepo;
    @Override
    public List<Domiciliario> findAll() {
        return domiciliarioRepo.findAll();
    }

    @Override
    public Optional<Domiciliario> findById(Long id) {
        return domiciliarioRepo.findById(id);
    }

    @Override
    public Domiciliario save(Domiciliario domiciliario) {
        return  domiciliarioRepo.save(domiciliario);
    }

    @Override
    public Optional<Domiciliario> findByEmail(String email) {
        return domiciliarioRepo.findByEmail(email);
    }
    
}
