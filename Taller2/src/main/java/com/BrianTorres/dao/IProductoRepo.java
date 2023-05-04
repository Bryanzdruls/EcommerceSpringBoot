package com.BrianTorres.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrianTorres.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto,Long>{
    
}
