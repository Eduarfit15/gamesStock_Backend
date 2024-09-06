package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.*;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.DetalleVentaRepository;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.ProductoRespository;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.UserRepository;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.VentaRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRespository productoRespository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Override
    @Transactional
    public void saveDetalleYVenta(String username, List<Long> producto,
                                  double totalVenta, List<Integer> catidad) {
        Optional<User> user=userRepository.findByUsuario(username);
        
        Date utilDate=new Date();

        if(user.isPresent()){
            User bdUser=user.orElseThrow();
            Ventas ventas=new Ventas();
            ventas.setFechaVenta(new java.sql.Date(utilDate.getTime()));
            ventas.setTotalVenta(totalVenta);
            ventas.setUsuario(bdUser);

            ventaRepository.save(ventas);

            for(int i=0; i<producto.size();i++){
                
                Optional<Producto> producto1=productoRespository.findById(producto.get(i));

                if(producto1.isPresent()){
                    
                    Producto producto2=producto1.orElseThrow();
                    DetalleVenta deta=new DetalleVenta();
                    deta.setCant_vendida(catidad.get(i));
                    deta.setPrecio_producto(producto2.getPrecio());
                    deta.setSub_total(catidad.get(i)*producto2.getPrecio());
                    deta.setVentas(ventas);
                    deta.setProductos(producto2);

                    discountStock(catidad.get(i),producto.get(i));
                    detalleVentaRepository.save(deta);
                }

            }
        }
    }
    @Override
    @Transactional
    public void discountStock(int stock, Long id) {
        
        productoRespository.discountStock(stock, id);
    }

    @Override
    public List<MisComprasRequest> findCompras(String username) {

        
        return ventaRepository.finCompras(username);

    }


}
