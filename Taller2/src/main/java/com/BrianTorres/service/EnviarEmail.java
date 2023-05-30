package com.BrianTorres.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.BrianTorres.model.Carrito;
import com.BrianTorres.model.Cliente;
import com.BrianTorres.model.Domiciliario;
import com.BrianTorres.model.Pedido;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnviarEmail {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private IClienteService clienteService;
    public void sendListEmail(Cliente cliente, List<Carrito> detalle){
        MimeMessage message = javaMailSender.createMimeMessage();
        Cliente Admin = clienteService.findById(Long.parseLong("1")).get();

        
        String mensaje= "";
        
        for (Carrito carrito : detalle) {
            mensaje = mensaje +(
                "Nombre : "+carrito.getNombreProducto()+". "
                +"Descripcion : "+ carrito.getProducto().getDescripcion()
                +". Cantidad : "+carrito.getCantidad()+" unidades.\n");
        }



        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Admin.getEmail());
            helper.setTo(cliente.getEmail());
            helper.setSubject("Prueba email Automatico");
            helper.setText("Usted compro: \n"+mensaje
                            
            
            );
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
    public void emailParaDomiciliario(Domiciliario domiciliario, Pedido pedido){
        MimeMessage message = javaMailSender.createMimeMessage();
        Cliente Admin = clienteService.findById(Long.parseLong("1")).get();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Admin.getEmail());
            helper.setTo(domiciliario.getEmail());
            helper.setSubject("Envio pendiente");
            helper.setText("HOLA "+domiciliario.getNombre()+" "+domiciliario.getApellido()+
            " Favor enviar el pedido con id: "+pedido.getId());
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
}
