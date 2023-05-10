package com.BrianTorres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="carrito")
public class Carrito {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombreProducto;


    private Integer cantidad;
    private Integer valorPedido;
    private Integer totalPorPedido;


    @ManyToOne
    private Pedido pedido;
    


    @ManyToOne
	private Producto producto;

    //metodos

    public Carrito() {
    }

    public Carrito(Long id, Integer cantidad, Integer valorPedido) {
        this.id = id;
        this.cantidad = cantidad;
        this.valorPedido = valorPedido;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getValorPedido() {
        return this.valorPedido;
    }

    public void setValorPedido(Integer valorPedido) {
        this.valorPedido = valorPedido;
    }

    public Carrito id(Long id) {
        setId(id);
        return this;
    }

    public Carrito cantidad(Integer cantidad) {
        setCantidad(cantidad);
        return this;
    }

    public Carrito valorPedido(Integer valorPedido) {
        setValorPedido(valorPedido);
        return this;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public Integer getTotalPorPedido() {
        return totalPorPedido;
    }

    public void setTotalPorPedido(Integer totalPorPedido) {
        this.totalPorPedido = totalPorPedido;
    }
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Carrito)) {
            return false;
        }
        Carrito carrito = (Carrito) o;
        return Objects.equals(id, carrito.id) && Objects.equals(cantidad, carrito.cantidad) && Objects.equals(valorPedido, carrito.valorPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, valorPedido);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", valorPedido='" + getValorPedido() + "'" +
            "}";
    }
    

}
