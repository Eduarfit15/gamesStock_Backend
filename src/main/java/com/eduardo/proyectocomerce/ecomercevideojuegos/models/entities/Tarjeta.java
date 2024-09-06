package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tarjeta")
public class Tarjeta {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private Long n_tarjeta;


    @NotNull
    private String f_vencimiento;


    @NotNull
    private int cvv;


    @NotNull
    private String correo;

    
    @NotEmpty
    private String tipo;

    @ManyToOne
    @JoinColumn(name="IDusuario")
    private User user;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getN_tarjeta() {
        return n_tarjeta;
    }


    public void setN_tarjeta(Long n_tarjeta) {
        this.n_tarjeta = n_tarjeta;
    }


    public String getF_vencimiento() {
        return f_vencimiento;
    }


    public void setF_vencimiento(String f_vencimiento) {
        this.f_vencimiento = f_vencimiento;
    }


    public int getCvv() {
        return cvv;
    }


    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
    

    
}
