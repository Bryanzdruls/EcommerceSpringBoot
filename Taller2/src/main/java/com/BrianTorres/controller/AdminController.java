package com.BrianTorres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BrianTorres.model.Producto;

import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IPedidoService;
import com.BrianTorres.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdminController {
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IPedidoService pedidoService;
    //@Autowired
   // private ICarritoService carritoService;
    @GetMapping("/home")
    public String home(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home";
    }

    @GetMapping("/clientes")
    public String verClientes(Model model){
        model.addAttribute("clientes", clienteService.findAll());
        return "/administrador/clientes";
    }
    @GetMapping("/pedidos")
    public String verPedidos(Model model){
        model.addAttribute("pedidos", pedidoService.findAll());
        return "/administrador/pedidos";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalles(Model model, @PathVariable Long id){
        model.addAttribute("pedidosDetalle", pedidoService.findById(id).get().getCarrito());
        return "/administrador/detallepedido";
    }

}
