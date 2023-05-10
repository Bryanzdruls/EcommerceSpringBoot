package com.BrianTorres.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Pedido;
import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IPedidoService;

import jakarta.servlet.http.HttpSession;


@Controller

@RequestMapping("/cliente")
public class ClienteController {
   
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IPedidoService pedidoService;

        
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/registro")
    public String crear(){
        return "cliente/registro";
    }
    
    @PostMapping("/guardar")
    public String guardar(Cliente cliente){
        logger.info("Cliente registro: {}", cliente);
        cliente.setRol("CLIENTE");
        //cliente.setPass();
        clienteService.save(cliente);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "/cliente/login";
    }

    @PostMapping("/ingresar")
    public String ingresar(Cliente cliente , HttpSession httpSession){
        Optional<Cliente> usuario = clienteService.findByEmail(cliente.getEmail());
        logger.info("Accesso : {}", usuario);

        if (usuario.isPresent()) {
			httpSession.setAttribute("idcliente", usuario.get().getId());
			
			if (usuario.get().getRol().equalsIgnoreCase("ADMIN")) {
				return "redirect:/administrador/home";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
    }

    @GetMapping("/pedidos")
    public String obtenerPedidos(Model model, HttpSession session){
        model.addAttribute("session",session.getAttribute("idusuario"));
        Cliente cliente = clienteService.findById(Long.parseLong(session.getAttribute("idusuario").toString())).get();
       List<Pedido> pedidos = pedidoService.findByCliente(cliente);
        logger.info("pedidos {}", pedidos);

        model.addAttribute("pedidos", pedidos);
        return "cliente/pedido";
    }
    @GetMapping("carrito/{id}")
    public String carritoPedido(@PathVariable Long id, HttpSession session, Model model){
        logger.info("id de la orden {}",id);
        Optional<Pedido> pedido = pedidoService.findById(id);
        model.addAttribute("carrito",pedido.get().getCarrito());

        model.addAttribute("session",session.getAttribute("idusuario"));
        return "cliente/detallePedido";
    }
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession httpSession){
        httpSession.removeAttribute("idusuario");
        return "redirect:/";
    }

}
