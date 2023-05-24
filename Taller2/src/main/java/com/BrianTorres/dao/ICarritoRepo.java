package com.BrianTorres.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.BrianTorres.model.Carrito;


public interface ICarritoRepo  extends JpaRepository<Carrito,Long>{
    
}
