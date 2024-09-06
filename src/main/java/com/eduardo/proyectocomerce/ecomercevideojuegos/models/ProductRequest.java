package com.eduardo.proyectocomerce.ecomercevideojuegos.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;

public class ProductRequest {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDproducto;


    @Column(unique = true)
    private String nombre;


    @NotEmpty
 
    private double precio;


    @NotEmpty
 
    private int stock;

    
    @NotEmpty
    private String categoria;


    @NotEmpty
    private MultipartFile imagen;


    public Long getIDproducto() {
        return IDproducto;
    }

    public void setIDproducto(Long iDproducto) {
        IDproducto = iDproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }


}
