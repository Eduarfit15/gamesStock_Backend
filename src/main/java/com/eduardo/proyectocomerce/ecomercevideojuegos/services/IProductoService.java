package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;



import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Producto;

public interface IProductoService {
    


    List<Producto> findAll();
    List<Producto> findWithoutState();

    
    Optional<Producto> findById(Long id);
    Optional<Producto> findByIdAll(Long id);

    void deshabilitarProdu(Long estado,Long productoid);

   Producto save(String nombre, double precio, int stock, String Categoria,MultipartFile imagen);

    Optional<Producto> update(Long id,String nombre,double precio,int stock, String Categoria,MultipartFile imagen);

    

    Optional<Producto> fidNameAgain(String name);
    

    
}
