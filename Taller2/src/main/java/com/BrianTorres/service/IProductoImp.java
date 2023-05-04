package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.IProductoRepo;
import com.BrianTorres.model.Producto;

@Service
public class IProductoImp implements IProductoService{

    @Autowired
    private IProductoRepo productoRepo;
    @Override
    public Producto save(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Optional<Producto> get(Long id) {
        return productoRepo.findById(id);
    }

    @Override
    public void update(Producto producto) {
       productoRepo.save(producto);
    }

    @Override
    public void delete(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepo.findAll();
    }
    
}
