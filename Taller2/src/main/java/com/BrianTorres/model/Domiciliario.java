package com.BrianTorres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Objects;



@Entity
@Table(name="domiciliario")
public class Domiciliario {
    //atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long id;
    @NotEmpty(message =  "Debes especificar el usuario")
    private String nombre;
    @NotEmpty(message =  "Debes especificar el usuario")
    private String apellido;
    @NotEmpty(message = "Debes especificar el email")
    @Email
    private String email;

    @OneToMany(mappedBy = "domi")
    private List<Pedido> pedidosAsignados;
    //metodos

    public Domiciliario() {
    }

    public Domiciliario(Long id, String nombre, String apellido, String email, List<Pedido> pedidosAsignados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pedidosAsignados = pedidosAsignados;
    }
    public Domiciliario(Long id, String nombre, String apellido, String email, Pedido pedidoUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        pedidosAsignados.add(pedidoUnitario);
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

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidosAsignados() {
        return this.pedidosAsignados;
    }

    public void setPedidosAsignados(List<Pedido> pedidosAsignados) {
        this.pedidosAsignados = pedidosAsignados;
    }
    public void setPedidosAsignados(Pedido pedidoUnitario) {
        this.pedidosAsignados.add(pedidoUnitario);
    }

    public Domiciliario id(Long id) {
        setId(id);
        return this;
    }

    public Domiciliario nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Domiciliario apellido(String apellido) {
        setApellido(apellido);
        return this;
    }

    public Domiciliario email(String email) {
        setEmail(email);
        return this;
    }

    public Domiciliario pedidosAsignados(List<Pedido> pedidosAsignados) {
        setPedidosAsignados(pedidosAsignados);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Domiciliario)) {
            return false;
        }
        Domiciliario domiciliario = (Domiciliario) o;
        return Objects.equals(id, domiciliario.id) && Objects.equals(nombre, domiciliario.nombre) && Objects.equals(apellido, domiciliario.apellido) && Objects.equals(email, domiciliario.email) && Objects.equals(pedidosAsignados, domiciliario.pedidosAsignados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, pedidosAsignados);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", email='" + getEmail() + "'" +
            ", pedidosAsignados='" + getPedidosAsignados() + "'" +
            "}";
    }


    
}
