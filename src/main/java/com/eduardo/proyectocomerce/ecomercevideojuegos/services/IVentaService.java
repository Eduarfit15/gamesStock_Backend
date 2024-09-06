package com.eduardo.proyectocomerce.ecomercevideojuegos.services;


import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.MisComprasRequest;


import java.util.List;


public interface IVentaService {

    void saveDetalleYVenta(String username, List<Long> producto,
                            double totalVenta, List<Integer> catidad);


    void discountStock(int stock, Long id);
    List<MisComprasRequest> findCompras(String username);

}
