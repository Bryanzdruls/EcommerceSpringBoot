package com.BrianTorres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Pedido;

public interface IPedidoRepo extends JpaRepository<Pedido,Long>{
    List<Pedido> findByCliente (Cliente cliente);
}
