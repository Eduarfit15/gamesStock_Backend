package com.eduardo.proyectocomerce.ecomercevideojuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Tarjeta;

import com.eduardo.proyectocomerce.ecomercevideojuegos.services.TarjetaService;



@RestController
@RequestMapping("/tarjeta")
@CrossOrigin(originPatterns = "*")
public class TarjetaController {
    

    @Autowired
    private TarjetaService service;


    @PostMapping("/{username}")
    public ResponseEntity<?> createTarjeta(@RequestBody Tarjeta tarjeta,@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tarjeta,username));
    }


    @GetMapping("/getCard/{username}")
    public ResponseEntity<?> getCard(@PathVariable String username){


         return ResponseEntity.status(HttpStatus.OK).body(service.getTarjeta(username));
        
    }


    @DeleteMapping("/deCard/{username}")
    public ResponseEntity<?> deletetCard(@PathVariable String username){


        service.deleteTarjeta(username);

         return ResponseEntity.noContent().build();
        
    }





}
