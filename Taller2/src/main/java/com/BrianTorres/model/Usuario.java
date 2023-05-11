package com.BrianTorres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;



@Entity
@Table(name="usuario")
public class Usuario {
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
    //metodos

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario() {
    }

    public Usuario(Long id, String user, String pass, String email) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario id(Long id) {
        setId(id);
        return this;
    }

    public Usuario user(String user) {
        setUser(user);
        return this;
    }

    public Usuario pass(String pass) {
        setPass(pass);
        return this;
    }

    public Usuario email(String email) {
        setEmail(email);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(user, usuario.user) && Objects.equals(pass, usuario.pass) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, pass, email);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", pass='" + getPass() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
    
}
