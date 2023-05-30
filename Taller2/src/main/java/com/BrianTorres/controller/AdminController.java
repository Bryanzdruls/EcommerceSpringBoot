package com.BrianTorres.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BrianTorres.model.Domiciliario;
import com.BrianTorres.model.Producto;

import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IDomiciliarioService;
import com.BrianTorres.service.IPedidoService;
import com.BrianTorres.service.IProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/administrador")
public class AdminController {
    @Autowired
    private IProductoService productoService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IPedidoService pedidoService;
    @Autowired
    private IDomiciliarioService domiciliarioService;
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
    @GetMapping("/listarDomiciliarios")  
    public String listarDomi(Model model){
        model.addAttribute("domiciliario", domiciliarioService.findAll());
        model.addAttribute("errorDomi", false);
        return "administrador/listarDomi";   
    }
    @GetMapping("/domiciliarios")
    public String crearDomiciliario(Model model){
        if (domiciliarioService.findAll().isEmpty()) {
            Domiciliario domi= new Domiciliario();
            model.addAttribute("domiciliario", domi);
            return "administrador/domiciliarios";
        }else{
            model.addAttribute("errorDomi", !domiciliarioService.findAll().isEmpty());
            model.addAttribute("domiciliario", domiciliarioService.findAll());
            return "administrador/listarDomi";
        }
    }

    @PostMapping("/guardarDomiciliario")
    public String guardarDomiciliario(@Valid Domiciliario domiciliario,BindingResult bindingResult,Model model){
        Optional<Domiciliario> domiConEmail = domiciliarioService.findByEmail(domiciliario.getEmail());
        Boolean emailExiste = domiConEmail.isPresent();
        if (bindingResult.hasErrors()||emailExiste) {
            if (emailExiste) {
                model.addAttribute("emailError", emailExiste);
                return "administrador/domiciliarios";
            }
            model.addAttribute("domiciliario",domiciliario);
            return "administrador/domiciliarios";
        }
        domiciliarioService.save(domiciliario);
        return "administrador/listarDomiciliarios";
    }
}
