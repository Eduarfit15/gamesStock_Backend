package com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities;

public interface TarjetaRequest {

    Long getN_tarjeta();

    String getF_vencimiento();

    Integer getCvv();

    String getCorreo();

    String getTipo();
    
}
