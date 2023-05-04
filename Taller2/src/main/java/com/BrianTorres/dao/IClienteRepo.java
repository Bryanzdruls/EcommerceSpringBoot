package com.BrianTorres.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrianTorres.model.Cliente;

public interface IClienteRepo  extends JpaRepository<Cliente,Long>{
    Optional<Cliente> findByEmail(String email);
}
