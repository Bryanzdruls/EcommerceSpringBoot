package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;


import com.BrianTorres.model.Cliente;

public interface IClienteService {
    List<Cliente> findAll();
	Optional<Cliente> findById(Long id);
	Cliente save (Cliente cliente);
	Optional<Cliente> findByEmail(String email);
	void encriptar();
}
