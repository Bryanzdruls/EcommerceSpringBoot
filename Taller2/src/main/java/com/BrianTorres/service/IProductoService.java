package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import com.BrianTorres.model.Producto;

public interface IProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Long id);
    public void update(Producto producto);
	public void delete(Long id);
	public List<Producto> findAll();
}
