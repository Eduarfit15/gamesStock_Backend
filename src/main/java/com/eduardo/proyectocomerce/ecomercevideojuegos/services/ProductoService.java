package com.eduardo.proyectocomerce.ecomercevideojuegos.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Producto;

import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.ProductoRespository;

import jakarta.transaction.Transactional;


@Service
public class ProductoService implements IProductoService{
    


    @Autowired
    private ProductoRespository productoRes;


    @Override
    @Transactional
    public List<Producto> findAll() {
        
        return (List<Producto>)productoRes.findAll();
    }

    @Override
    @Transactional
    public List<Producto> findWithoutState() {
        

        return (List<Producto>) productoRes.findWithoutState();
    }

    @Override
    @Transactional
    public Optional<Producto> findById(Long id) {
 
        return productoRes.findByIdQ(id);
    }


    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<Producto> findByIdAll(Long id) {
 
        return productoRes.findById(id);
    }


    @Override
    @Transactional
    public void deshabilitarProdu(Long estado,Long id) {
        
        productoRes.deshabilitarProducto(estado,id);

    }

    @Override
    @Transactional
    public Producto save(String nombre, double precio, int stock, String Categoria,MultipartFile imagen) {

        Producto producto2=new Producto();

        producto2.setNombre(nombre);
        producto2.setPrecio(precio);
        producto2.setStock(stock);
        producto2.setCategoria(Categoria);
        producto2.setEstado(1);


            if(imagen !=null && !imagen.isEmpty()){
                try {

                    producto2.setImagen(imagen.getBytes());

                } catch (Exception e) {
                    throw new RuntimeException("Error al codificar la imagen en base 64"+e.getMessage());
                }
                
            }

            return productoRes.save(producto2);

        
    }



    @Override
    public Optional<Producto> update(Long id,String nombre, double precio, int stock, String Categoria, MultipartFile imagen) {
        

        Optional<Producto> o= findByIdAll(id);

        if(o.isPresent()){

            Producto producto=o.orElseThrow();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setCategoria(Categoria);
            producto.setEstado(o.get().getEstado());

         if(imagen!=null &&!imagen.isEmpty()){

            try {

                producto.setImagen(imagen.getBytes());
                return Optional.of(productoRes.save(producto));

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage().toString());
            }
         }
                 
        }
         return Optional.empty();
    }

    @Override
    public Optional<Producto> fidNameAgain(String name) {
        
        return productoRes.finNameAgain(name);
    }

   

    


}
