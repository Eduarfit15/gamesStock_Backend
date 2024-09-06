package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.Optional;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Tarjeta;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.TarjetaRequest;

public interface ITarjeta {


    Optional<Tarjeta> save(Tarjeta tarjeta,String username);
    TarjetaRequest getTarjeta(String username);
    
    void deleteTarjeta(String username);
}
