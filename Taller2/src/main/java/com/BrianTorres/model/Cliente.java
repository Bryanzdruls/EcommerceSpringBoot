package com.BrianTorres.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
    //atributos


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long id;
    @NotEmpty(message =  "Debes especificar el usuario")
    private String user;
    @NotEmpty(message =  "Debes especificar la contraseña")
    private String pass;
    @NotEmpty(message = "Debes especificar el email")
    @Email
    private String email;
    

    private String rol;
    @NotEmpty(message = "Debes especificar el Nombre")
    private String nombre;
    @NotEmpty(message = "Debes especificar el Nombre")
    private String apellido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "campo requerido")
    private Date fechaNac;
    @NotBlank(message = "campo obligatorio")
    private String direccion;
    @NotNull(message = "ingrese su numero celular correctamente")
    private Integer telefono;
    
	@OneToMany(mappedBy = "cliente")
	private List<Producto> productos;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;





    public List<Pedido> getPedidos() {
        return pedidos;
    }


    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    //metodos

    public Cliente() {
    }


    public Cliente(Long id, String user, String pass, String email, String rol, String nombre, String apellido, Date fechaNac, String direccion, Integer telefono, List<Producto> productos, List<Pedido> pedidos) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = productos;
        this.pedidos = pedidos;
    }
    

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return this.fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return this.telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Cliente nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Cliente apellido(String apellido) {
        setApellido(apellido);
        return this;
    }

    public Cliente fechaNac(Date fechaNac) {
        setFechaNac(fechaNac);
        return this;
    }

    public Cliente direccion(String direccion) {
        setDireccion(direccion);
        return this;
    }

    public Cliente telefono(Integer telefono) {
        setTelefono(telefono);
        return this;
    }
    public String getRol() {
        return rol;
    }


    public void setRol(String rol) {
        this.rol = rol;
    }
    public List<Producto> getProductos() {
        return productos;
    }


    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getPass() {
        return pass;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(fechaNac, cliente.fechaNac) && Objects.equals(direccion, cliente.direccion) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, fechaNac, direccion, telefono);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fechaNac='" + getFechaNac() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
 
    
}
