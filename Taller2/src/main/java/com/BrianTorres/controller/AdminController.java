package com.BrianTorres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BrianTorres.model.Producto;
import com.BrianTorres.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdminController {
    @Autowired
    private IProductoService productoService;
    @GetMapping("/home")
    public String home(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home";
    }

}
