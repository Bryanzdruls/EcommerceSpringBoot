package com.BrianTorres.controller;


import java.io.IOException;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Producto;

import com.BrianTorres.service.IProductoService;
import com.BrianTorres.service.UploadFileService;



@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    //private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private UploadFileService subirArchivo;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("producto", productoService.findAll());
        return"producto/listar";
    }

    @GetMapping("/crear")
    public String crear(){
        return "producto/crear";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto,@RequestParam("img") MultipartFile file) throws IOException{       
        producto.setOferta(false) ;
        Cliente u = new Cliente();
        u.setId(Long.parseLong("1"));       
        producto.setCliente(u);
        
        //imagen
        if (producto.getId()==null) {
            //cuando se crea el producto
            String nombreImg =subirArchivo.guardarImagen(file);
            producto.setImagen(nombreImg);
        }else{
            
        }
        productoService.save(producto);
        return "redirect:/producto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Producto producto = new Producto();
        Optional<Producto> opProducto= productoService.get(id); 
        
        producto=opProducto.get();
        model.addAttribute("producto", producto);

        return "producto/edit";
    }

    @PostMapping("/actualizar")
    public String actualizar(Producto producto, @RequestParam("img") MultipartFile file) throws IOException{
        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();

        
        //cuando se edita el producto
        if (file.isEmpty()) {
            //misma imagen           
            producto.setImagen(p.getImagen());
        } else {
            //diff Imagen
            //eliminar cuando la imagen no sea por defecto
            if (!p.getImagen().equals("default.jpg")) {
                subirArchivo.borrarImagen(p.getImagen());
            } 
            String nombreImg =subirArchivo.guardarImagen(file);
            producto.setImagen(nombreImg);
        }
        producto.setCliente(p.getCliente());          
        productoService.update(producto);
        return "redirect:/producto/listar";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id){

        Producto p = new Producto();
        p= productoService.get(id).get();
        //eliminar cuando la imagen no sea por defecto
        if (!p.getImagen().equals("default.jpg")) {
            subirArchivo.borrarImagen(p.getImagen());
        } 
        productoService.delete(id);
        return "redirect:/producto/listar";
    }
}
