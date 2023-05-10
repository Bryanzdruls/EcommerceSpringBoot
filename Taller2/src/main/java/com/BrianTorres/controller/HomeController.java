package com.BrianTorres.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BrianTorres.model.Carrito;
import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Pedido;
import com.BrianTorres.model.Producto;
import com.BrianTorres.service.ICarritoService;
import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IPedidoService;
import com.BrianTorres.service.IProductoService;


import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private IProductoService productoService;

    //para almacenar los detalles de la orden
    List<Carrito> detalle = new ArrayList<Carrito>();

    //datos pedido
    Pedido pedido = new Pedido();

    @Autowired
    private IClienteService clienteService;
    
    @Autowired
    private IPedidoService pedidoService;
    @Autowired
    private ICarritoService carritoService;
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "cliente/home";
    }

    @GetMapping("detalleProducto/{id}")
    public String detalleProducto(@PathVariable Long id, Model model)
    {
        Producto producto = new Producto();
        Optional<Producto> productoOptional=productoService.get(id);
        producto=productoOptional.get();
        model.addAttribute("producto", producto);
        return "cliente/productoHome";
    }

    @PostMapping(value="/carrito")
    public String carrito(@RequestParam Long id, @RequestParam Integer cantidad, Model model) {
        Carrito carrito = new Carrito();
        Producto producto = new Producto();
        int total=0;

        Optional<Producto> opProducto = productoService.get(id);

        producto = opProducto.get();

        carrito.setCantidad(cantidad);
        carrito.setValorPedido(producto.getPrecio());//precio unitario
        carrito.setNombreProducto(producto.getNombre());
        carrito.setTotalPorPedido((int)(cantidad*producto.getPrecio()));
        carrito.setProducto(producto);

        //validar que le producto no se añada 2 veces
		Long idProducto=producto.getId();
		boolean ingresado=detalle.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalle.add(carrito);
		}
        total = detalle.stream().mapToInt(dt ->dt.getTotalPorPedido ()).sum();

        pedido.setSubtotal(total);
        model.addAttribute("carrito", detalle);
        model.addAttribute("pedido", pedido);
        return "cliente/carrito";
    }

    @GetMapping("/eliminar/carrito/{id}")
    public String eliminar(@PathVariable Long id,Model model){
        //lista nueva
        List<Carrito> CarroNuevo = new ArrayList<Carrito>();
        int sum=0;
        for (Carrito carrito : detalle) {
            if (carrito.getProducto().getId()!=id) {
                CarroNuevo.add(carrito);
            }
        }        
        detalle = CarroNuevo;
        
        sum = detalle.stream().mapToInt(dt ->dt.getTotalPorPedido()).sum();
        
        
        pedido.setSubtotal(sum);
        

        model.addAttribute("carrito", detalle);
        model.addAttribute("pedido", pedido);
        
        return "cliente/carrito";
    }
    @GetMapping(value="getCarrito")
    public String getCarrito(Model model) {
        model.addAttribute("carrito", detalle);
        model.addAttribute("pedido", pedido);
        return "cliente/carrito";
    }
    
   @GetMapping("/pedido")
    public String verPedido(Model model){
        Cliente cliente = clienteService.findById(Long.parseLong("1")).get();
        model.addAttribute("carrito", detalle);
        model.addAttribute("pedido", pedido);
        model.addAttribute("cliente", cliente);
        return "cliente/resumenpedido";
    }
    @GetMapping("/guardarPedido")
    public String guardarPedido(){
        Date fechaDePedido = new Date();
        pedido.fechaPedido(fechaDePedido);

        //usuario que realiza la orden
        Cliente cliente = clienteService.findById(Long.parseLong("1")).get();
        pedido.setCliente(cliente);

        pedido.setTotal(pedido.getSubtotal()+2000);
        pedidoService.save(pedido);
        

        //guardar los detalles
        for (Carrito carrito : detalle) {
            carrito.setPedido(pedido);
            carritoService.save(carrito);
        }

        //limpiar valores
        pedido = new Pedido();
        detalle.clear();
        return "redirect:/"; 
    }
    @PostMapping("/buscarproducto")
    public String buscarProducto(@RequestParam(value="nombre") String busqueda, Model model){
        List<Producto> productos=productoService.findAll().stream()
        .filter(p ->p.getNombre().contains(busqueda)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "cliente/home";
    }
    
}
