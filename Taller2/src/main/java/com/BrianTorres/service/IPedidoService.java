package com.BrianTorres.service;
import java.util.List;
import java.util.Optional;



import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Pedido;

public interface IPedidoService {
    List<Pedido> findAll();
	Optional<Pedido> findById(Long id);
	Pedido save (Pedido pedido);
	String generarNumeroOrden();
	List<Pedido> findByCliente (Cliente cliente);
}
