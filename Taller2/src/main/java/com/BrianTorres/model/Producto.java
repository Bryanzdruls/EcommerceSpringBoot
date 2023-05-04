package com.BrianTorres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Ingrese el nombre del producto")
    private String nombre;
    @NotBlank(message = "Ingrese la descripcion del producto")
    private String descripcion;
    @NotBlank(message = "Ingrese el codigo del producto")
    private String codigo;
    @NotNull(message = "ingrese el precio del producto")
    private Integer precio;
    @NotNull(message="ingrese la cantidad del producto")
    private Integer existencias;
    
    private boolean oferta;


    //@NotNull(message = "Ingrese una imagen")
    private String imagen;
    
    
    @ManyToOne
	private Cliente cliente;

    

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, String codigo, Integer precio, Integer existencias, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getPrecio() {
        return this.precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getExistencias() {
        return this.existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    public Producto codigo(String codigo) {
        setCodigo(codigo);
        return this;
    }

    public Producto precio(Integer precio) {
        setPrecio(precio);
        return this;
    }

    public Producto existencias(Integer existencias) {
        setExistencias(existencias);
        return this;
    }

    public Producto imagen(String imagen) {
        setImagen(imagen);
        return this;
    }
    
    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(descripcion, producto.descripcion) && Objects.equals(codigo, producto.codigo) && Objects.equals(precio, producto.precio) && Objects.equals(existencias, producto.existencias) && Objects.equals(imagen, producto.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, codigo, precio, existencias, imagen);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", existencias='" + getExistencias() + "'" +
            ", imagen='" + getImagen() + "'" +
            "}";
    }

    
}
