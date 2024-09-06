package com.eduardo.proyectocomerce.ecomercevideojuegos.repositories;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.MisComprasRequest;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Ventas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface VentaRepository extends CrudRepository<Ventas,Long> {



    @Query(value = "select v.n_venta, v.fecha_venta,v.total_venta, " +
            "pro.imagen,pro.nombre,pro.precio,de.cant_vendida,de.sub_total " +
            "from venta v inner join users us on v.idusuario=us.idusuario inner join detalle_venta " +
            "de on de.n_venta=v.n_venta inner join producto pro on de.idproducto=pro.idproducto" +
            " where us.usuario=?1",nativeQuery = true)
    List<MisComprasRequest> finCompras(String username);

}
