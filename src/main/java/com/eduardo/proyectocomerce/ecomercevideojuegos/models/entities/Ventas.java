package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;


@Entity
@Table(name="venta")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nVenta;

    @NotNull
    private Date fechaVenta;

    @NotNull
    private double totalVenta;

    @ManyToOne
    @JoinColumn(name="IDusuario")
    private User usuario;


    public Long getnVenta() {
        return nVenta;
    }

    public void setnVenta(Long nVenta) {
        this.nVenta = nVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
