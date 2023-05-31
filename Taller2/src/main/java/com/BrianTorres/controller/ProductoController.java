package com.BrianTorres.controller;


import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;


//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Producto;
import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IProductoService;
import com.BrianTorres.service.UploadFileService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/producto")
public class ProductoController {
    

    @Autowired
    private IProductoService productoService;

    @Autowired
    private UploadFileService subirArchivo;

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("producto", productoService.findAll());
        return "/producto/listar";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        Producto producto =new Producto();
        model.addAttribute("producto", producto);
        return "/producto/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto,BindingResult bindingResult,@RequestParam("imagen") MultipartFile file,   HttpSession session,SessionStatus estado, Model model) throws IOException{       
        if(!validarExtension(file)&& !file.isEmpty()){
            model.addAttribute("producto", producto);
            return "7producto/crear";
        }
        if(bindingResult.hasErrors()){
            if (bindingResult.hasFieldErrors("codigo")||
                bindingResult.hasFieldErrors("nombre")||
                bindingResult.hasFieldErrors("descripcion")||
                bindingResult.hasFieldErrors("precio")||
                bindingResult.hasFieldErrors("existencias")) {
                model.addAttribute("producto", producto);
                return "/producto/crear";
        }
            }else {
                
            
        }
        if (producto.getId()==null) {
            //cuando se crea el producto
            String nombreImg=subirArchivo.guardarImagen(file, producto);
            producto.setImagen(nombreImg);
        }
        
    
        producto.setOferta(false) ;
        Cliente u = clienteService.findById(Long.parseLong(session.getAttribute("idcliente").toString())).get();
        producto.setCliente(u);
       
        //imagen


        productoService.save(producto);
        estado.setComplete();
        return "redirect:/producto/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Producto producto = new Producto();
        Optional<Producto> opProducto= productoService.get(id); 
        
        producto=opProducto.get();
        model.addAttribute("producto", producto);

        return "/producto/edit";
    }

    @PostMapping("/actualizar")
    public String actualizar(@Valid Producto producto,BindingResult bindingResult, @RequestParam("img") MultipartFile file, Model model) throws IOException{
        if(bindingResult.hasErrors()){
            if (bindingResult.hasFieldErrors("codigo")||
                bindingResult.hasFieldErrors("nombre")||
                bindingResult.hasFieldErrors("descripcion")||
                bindingResult.hasFieldErrors("precio")||
                bindingResult.hasFieldErrors("existencias")) {
                model.addAttribute("producto", producto);
                return "/producto/edit";
             }
           
        }
        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();

        
        //cuando se edita el producto
        if (file.isEmpty()) {
            //misma imagen           
            producto.setImagen(p.getImagen());
        } else {
            //diff Imagen
            //eliminar cuando la imagen no sea por defecto
            if(!p.getImagen().equals("default.jpg")) {
                subirArchivo.borrarImagen(p.getImagen());
            } 
            String nombreImg =subirArchivo.guardarImagen(file, producto);
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
    public Boolean validarExtension(MultipartFile file){
        try {
            ImageIO.read(file.getInputStream()).toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
