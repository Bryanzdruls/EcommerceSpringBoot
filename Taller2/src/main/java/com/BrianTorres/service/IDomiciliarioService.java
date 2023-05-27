package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import com.BrianTorres.model.Domiciliario;

public interface IDomiciliarioService {
    List<Domiciliario> findAll();
	Optional<Domiciliario> findById(Long id);
	Domiciliario save (Domiciliario domiciliario);
	Optional<Domiciliario> findByEmail(String email);
}
