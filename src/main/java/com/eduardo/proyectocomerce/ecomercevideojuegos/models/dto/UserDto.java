package com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto;

public class UserDto {
    

    private Long IDusuario;
    private String usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String nroCelular;
    private boolean admin;

    
    public UserDto() {
    }

   
    public UserDto(Long iDusuario, String usuario, String nombre, String apellido, String correo, String nroCelular,
            boolean admin) {
        IDusuario = iDusuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nroCelular = nroCelular;
        this.admin = admin;
    }

    public Long getIDusuario() {
        return IDusuario;
    }

    public void setIDusuario(Long iDusuario) {
        IDusuario = iDusuario;
    }

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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    

    






}
