package com.eduardo.proyectocomerce.ecomercevideojuegos.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Tarjeta;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.TarjetaRequest;

public interface TarjetaRepository extends CrudRepository<Tarjeta,Long>{
    


    @Query(value = "select ta.n_tarjeta, ta.f_vencimiento,ta.cvv,ta.correo,ta.tipo from tarjeta ta inner join users us on ta.idusuario=us.idusuario where us.usuario=?1",nativeQuery = true)
    TarjetaRequest getTarjeta(String usuario);


    @Query(value = "select ta.id, ta.n_tarjeta, ta.f_vencimiento,ta.cvv,ta.correo,ta.tipo, ta.idusuario from tarjeta ta inner join users us on ta.idusuario=us.idusuario where us.usuario=?1",nativeQuery = true)
    Tarjeta searchTarjeta(String usuario);

    @Query(value = "DELETE tarjeta WHERE id =?1",nativeQuery = true)
    void deleteTarjeta(Long id);

}
