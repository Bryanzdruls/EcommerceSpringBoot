package com.BrianTorres.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrianTorres.model.Domiciliario;

public interface IDomiciliarioRepo extends JpaRepository<Domiciliario, Long >{
    Optional<Domiciliario> findByEmail(String email);
}
