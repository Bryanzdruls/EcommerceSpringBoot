package com.BrianTorres.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.ICarritoRepo;
import com.BrianTorres.model.Carrito;

@Service
public class ICarritoImp implements ICarritoService {

    @Autowired
    private ICarritoRepo carritoRepo;
    @Override
    public Carrito save(Carrito carrito) {
        return carritoRepo.save(carrito);
    }
    
}
