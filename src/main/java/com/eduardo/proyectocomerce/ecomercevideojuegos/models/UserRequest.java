package com.eduardo.proyectocomerce.ecomercevideojuegos.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotEmpty;


public class UserRequest {
    

    
    @Column(unique = true)
    private String usuario;
  
    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String correo;

    @NotEmpty
    private String nroCelular;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(String nroCelular) {
        this.nroCelular = nroCelular;
    }

    
}
