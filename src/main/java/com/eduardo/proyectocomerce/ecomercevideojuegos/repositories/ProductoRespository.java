package com.eduardo.proyectocomerce.ecomercevideojuegos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Producto;
import org.springframework.data.repository.query.Param;

public interface ProductoRespository extends CrudRepository<Producto,Long> {
    


    @Query(value = "select * from producto where stock !=0 and estado !=0",nativeQuery = true)
    List<Producto> findWithoutState();

    @Query(value = "select * from producto where estado !=0 and idproducto=?1",nativeQuery = true)
    Optional<Producto> findByIdQ(Long id);

    @Query(value = "select * from producto where nombre=?1",nativeQuery = true)
    Optional<Producto> finNameAgain(String name);

    @Modifying
    @Query(value = ("update producto p set p.estado=?1 where p.idproducto=?2"),nativeQuery = true)
    void deshabilitarProducto(Long estado,Long productoid);

    @Modifying
    @Query(value = ("update producto p set p.stock=p.stock - :stock where p.idProducto=:productoId"),nativeQuery = true)
    void discountStock(@Param("stock") int stock,@Param("productoId") Long productoId);



   

}
