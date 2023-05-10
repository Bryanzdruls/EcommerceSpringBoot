package com.BrianTorres.model;



import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPedido;

    private Integer subtotal;
    private Integer total;

    @OneToMany(mappedBy = "pedido")
    private List<Carrito> carrito;

    @ManyToOne
    private Cliente cliente;




    public Pedido() {
    }

    public Pedido(Long id, Date fechaPedido, Integer subtotal, Integer total) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return this.fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Pedido id(Long id) {
        setId(id);
        return this;
    }

    public Pedido fechaPedido(Date fechaPedido) {
        setFechaPedido(fechaPedido);
        return this;
    }

    public Pedido subtotal(Integer subtotal) {
        setSubtotal(subtotal);
        return this;
    }

    public Pedido total(Integer total) {
        setTotal(total);
        return this;
    }
    public List<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Carrito> carrito) {
        this.carrito = carrito;
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
        if (!(o instanceof Pedido)) {
            return false;
        }
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(fechaPedido, pedido.fechaPedido) && Objects.equals(subtotal, pedido.subtotal) && Objects.equals(total, pedido.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaPedido, subtotal, total);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", fechaPedido='" + getFechaPedido() + "'" +
            ", subtotal='" + getSubtotal() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
    
}
