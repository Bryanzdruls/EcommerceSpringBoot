package com.BrianTorres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BrianTorres.model.Carrito;

@Repository
public interface ICarritoRepo  extends JpaRepository<Carrito,Long>{
    
}
