package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

import java.util.Date;

public interface MisComprasRequest {


     Long getN_venta();

     Date getFecha_venta();

     double getTotal_venta();

     byte[] getImagen();

     String getNombre();

     double getPrecio();

     int getCant_vendida();

     double getSub_total();


}
