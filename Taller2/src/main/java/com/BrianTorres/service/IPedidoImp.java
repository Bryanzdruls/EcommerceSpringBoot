package com.BrianTorres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BrianTorres.dao.IPedidoRepo;
import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Pedido;
@Service
public class IPedidoImp implements IPedidoService{

    @Autowired
    private IPedidoRepo pedidoRepo;
    @Override
    public List<Pedido> findAll() {
        return pedidoRepo.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepo.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    @Override
    public String generarNumeroOrden() {

        throw new UnsupportedOperationException("Unimplemented method 'generarNumeroOrden'");
    }

    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        return pedidoRepo.findByCliente(cliente);
    }
    
}
