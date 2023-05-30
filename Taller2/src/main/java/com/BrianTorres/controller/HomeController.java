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
import com.BrianTorres.model.Domiciliario;
import com.BrianTorres.model.Pedido;
import com.BrianTorres.model.Producto;
import com.BrianTorres.service.CrearPdf;
import com.BrianTorres.service.EnviarEmail;
import com.BrianTorres.service.ICarritoService;
import com.BrianTorres.service.IClienteService;
import com.BrianTorres.service.IDomiciliarioService;
import com.BrianTorres.service.IPedidoService;
import com.BrianTorres.service.IProductoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDomiciliarioService domiciliarioService;

    //para almacenar los detalles de la orden
    List<Carrito> detalle = new ArrayList<Carrito>();


    //Array id pal carrito
    List<Long> idProductos = new ArrayList<Long>();
    //datos pedido
    Pedido pedido = new Pedido();
    
    @Autowired
    private CrearPdf crearPdf;

    @Autowired
    private IClienteService clienteService;
    
    @Autowired
    private IPedidoService pedidoService;
    @Autowired
    private ICarritoService carritoService;
    @Autowired
    private EnviarEmail envioEmail;
    
    @GetMapping("")
    public String home(Model model, HttpSession session){
        System.out.println("el id es:"+session.getAttribute("idcliente"));

        
        model.addAttribute("productos", productoService.findAll());
        //sesion
        model.addAttribute("sesion", session.getAttribute("idcliente"));
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
    public String carrito(@RequestParam Long id, @RequestParam Integer cantidad, Model model, HttpSession session) {
        
        Carrito carrito = new Carrito();
        Producto producto = new Producto();
        int total=0;

        Optional<Producto> opProducto = productoService.get(id);

        producto = opProducto.get();
        Long idProductoSession = producto.getId();
        Boolean cantidadExedida=false;
        idProductos.add(idProductoSession);
        if (producto.getExistencias()<cantidad) {
            cantidadExedida=true;
            model.addAttribute("errorCantidad", cantidadExedida);
            model.addAttribute("producto", productoService.get(id).get());
            return "cliente/productoHome";
        }

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
        //sesion
        model.addAttribute("sesion", session.getAttribute("idcliente"));
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
    public String getCarrito(Model model, HttpSession session) {

        model.addAttribute("carrito", detalle);
        model.addAttribute("pedido", pedido);
    //sesion
    model.addAttribute("sesion", session.getAttribute("idcliente"));
        

        return "cliente/carrito";
    }
    
   @GetMapping("/pedido")
    public String verPedido(Model model,HttpSession session){
        Cliente cliente = clienteService.findById(Long.parseLong(session.getAttribute("idcliente").toString())).get();
        model.addAttribute("carrito", detalle);

        model.addAttribute("pedido", pedido);
        model.addAttribute("cliente", cliente);
        //sesion
        model.addAttribute("sesion", session.getAttribute("idcliente"));
        return "cliente/resumenpedido";
    }
    @GetMapping("/guardarPedido")
    public String guardarPedido(HttpSession session){


        Date fechaDePedido = new Date();
        pedido.fechaPedido(fechaDePedido);




        
        //usuario que realiza la orden
        Cliente cliente = clienteService.findById(Long.parseLong(session.getAttribute("idcliente").toString())).get();
        pedido.setCliente(cliente);
        
        System.out.println( detalle.toString());

        for (Carrito carrito1 : detalle) {
            for (int i=0;i<idProductos.size();i++) {
                Producto producto =productoService.get(idProductos.get(i)).get();
                if (carrito1.getProducto().getId().equals(idProductos.get(i))) {
                    producto.restarUnidades(carrito1.getCantidad());
                }
            } 
        }

        idProductos.clear();
        
        pedido.setTotal(pedido.getSubtotal()+2000);
        pedidoService.save(pedido);
        

        //guardar los detalles
        for (Carrito carrito : detalle) {
            
            carrito.setPedido(pedido);
            carritoService.save(carrito);
        }
        envioEmail.sendListEmail(cliente, detalle);


        //se asigna el domiciliario
        Optional<Domiciliario> domiOptional= domiciliarioService.findById(1l); 
        Domiciliario domi;
        if (!domiOptional.isPresent()) {
            domiciliarioService.defaultDomi(pedido);
            domi =domiciliarioService.findById(1l).get(); 
        }else{
            domi = domiOptional.get();
        }
        
        
        pedido.setDomi(domi);
        domi.setPedidosAsignados(pedido);
        envioEmail.emailParaDomiciliario(domi, pedido);

        String mensaje ="";

        for (Carrito carrito : detalle) {
            mensaje = mensaje +(
                "Nombre : "+carrito.getNombreProducto()+". "
                +"Descripcion : "+ carrito.getProducto().getDescripcion()
                +". Cantidad : "+carrito.getCantidad()+" unidades.\n");
        }
        try {
            crearPdf.generatePdf(mensaje, "C:/Users/eltio/Downloads/prueba.pdf");
        } catch (Exception e) {
            // TODO: handle exception
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
