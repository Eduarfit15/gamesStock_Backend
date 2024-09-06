package com.eduardo.proyectocomerce.ecomercevideojuegos.models;

public class VentaRequest {
    

    private String productos;

    private double total;

    private String cantidades;

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCantidades() {
        return cantidades;
    }

    public void setCantidades(String cantidades) {
        this.cantidades = cantidades;
    }


    
}
