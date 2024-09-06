package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name="detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @NotNull
    private int cant_vendida;

    @NotNull
    private double precio_producto;

    @NotNull
    private double sub_total;

    @NotNull
    @ManyToOne
    @JoinColumn(name="nVenta")
    private Ventas ventas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "IDproducto")
    private Producto productos;

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCant_vendida() {
        return cant_vendida;
    }

    public void setCant_vendida(int cant_vendida) {
        this.cant_vendida = cant_vendida;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }
}
