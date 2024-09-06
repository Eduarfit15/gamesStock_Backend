package com.eduardo.proyectocomerce.ecomercevideojuegos.controllers;



import com.eduardo.proyectocomerce.ecomercevideojuegos.models.VentaRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.MisComprasRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.UserRepository;

import com.eduardo.proyectocomerce.ecomercevideojuegos.services.VentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venta")
@CrossOrigin(originPatterns = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{username}")
    public ResponseEntity<?> guardarVenta(
      @PathVariable String username,
      @RequestBody VentaRequest venta) throws JsonProcessingException {

        String productosJson = venta.getProductos();
        double totalVenta = venta.getTotal();
        String cantidadesJson = venta.getCantidades();

        System.out.println(productosJson);
        System.out.println(totalVenta);
        System.out.println(cantidadesJson);
        List<Long> productos = new ObjectMapper().readValue(productosJson, new TypeReference<List<Long>>(){});
        List<Integer> cantidades = new ObjectMapper().readValue(cantidadesJson, new TypeReference<List<Integer>>(){});

        Optional<User> o=userRepository.findByUsuario(username);

        if (o.isPresent()){
            ventaService.saveDetalleYVenta(username,productos,totalVenta,cantidades);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }


    @GetMapping("/compras/{username}")
    public List<MisComprasRequest> findCompras(@PathVariable String username){

        return ventaService.findCompras(username);

    }
}
