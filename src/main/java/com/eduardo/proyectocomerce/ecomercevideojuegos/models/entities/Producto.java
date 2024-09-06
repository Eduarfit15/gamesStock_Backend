package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name ="producto")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDproducto;


    @Column(unique = true)
    private String nombre;

    private double precio;

    private int stock;

    @NotEmpty
    private String categoria;

    @NotEmpty(message = "La imagen es obligatoria")
    /* @Pattern(regexp = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)", message = "La imagen debe ser un archivo JPG, PNG, GIF o BMP") */
    private byte[] imagen;

    
    private int estado;



    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(Long iDproducto) {
        IDproducto = iDproducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

  

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    
    
}
