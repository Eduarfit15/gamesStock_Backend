package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import java.util.List;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.IUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="users")
public class User implements IUser{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDusuario;
    
    
    @Column(unique = true)
    private String usuario;

    @NotEmpty
    private String contra;


    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String correo;

    @NotEmpty
    private String nroCelular;



    @ManyToMany
    @JoinTable(name="users_roles",joinColumns = @JoinColumn(name="user_IDusuario"),
    inverseJoinColumns = @JoinColumn(name="role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_IDusuario","role_id"})})
    private List<Role> roles;


    @Transient //con esta anotacion hago que este atributo no este mapeado en la base de datos
    private boolean admin;


    
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

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    


}
