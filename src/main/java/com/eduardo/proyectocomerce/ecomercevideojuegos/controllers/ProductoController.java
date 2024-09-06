package com.eduardo.proyectocomerce.ecomercevideojuegos.controllers;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.eduardo.proyectocomerce.ecomercevideojuegos.models.ProductRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Producto;
import com.eduardo.proyectocomerce.ecomercevideojuegos.services.ProductoService;
import com.eduardo.proyectocomerce.ecomercevideojuegos.validation.ValidationResult;





@RestController
@RequestMapping("/products")
@CrossOrigin(originPatterns = "*")
public class ProductoController {


    @Autowired
    private ProductoService productoService;


    @GetMapping
    public List<Producto> list(){

        return productoService.findAll();
    }

    @GetMapping("/NoStateProducts")
    public List<Producto> findWithoutState(){

         return productoService.findWithoutState();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?>  search(@PathVariable Long id){
    
        Optional<Producto> pro= productoService.findById(id);

        if(pro.isPresent()){

            return ResponseEntity.ok(pro.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findNameAgain/{name}")
    public ResponseEntity<?> searchName(@PathVariable String name){


         Optional<Producto> pro= productoService.fidNameAgain(name);

         if (pro.isPresent()) {
            
            return ResponseEntity.ok(pro.orElseThrow());
         }

         return ResponseEntity.notFound().build();
    }


    @GetMapping("/findIdAll/{id}")
    public ResponseEntity<?>  searhById(@PathVariable Long id){
    
        Optional<Producto> pro= productoService.findByIdAll(id);

        if(pro.isPresent()){

            return ResponseEntity.ok(pro.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping ("/{estado}/{id}")
    public ResponseEntity<?> deshabilitar(@PathVariable Long estado , @PathVariable Long id){
        
        Optional<Producto> pro= productoService.findByIdAll(id);

        if(pro.isPresent()){

            productoService.deshabilitarProdu(estado,id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


 /*    @PostMapping("/crear")
    public ResponseEntity<?> save(@RequestPart ProductRequest product){
     
        ValidationResult result = validateProduct(product);
        if (!result.isValid()) {
            return ResponseEntity.badRequest().body(result.getErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(product));
    } */
    

    @PostMapping("/crear")
    public ResponseEntity<?> save(@RequestParam("nombre") String nombre, 
    @RequestParam("precio") double precio,
    @RequestParam("stock") int stock, @RequestParam("categoria") String categoria,
    @RequestParam("imagen") MultipartFile imagen
    ){

        ProductRequest p=new ProductRequest();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setCategoria(categoria);
        p.setImagen(imagen);
        ValidationResult result=validateProduct(p);
        if (!result.isValid()) {
            return ResponseEntity.badRequest().body(result.getErrors());
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(nombre,precio,stock,categoria,imagen));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    
        
    }

 

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable Long id,
    @RequestParam("nombre") String nombre, 
    @RequestParam("precio") double precio,
    @RequestParam("stock") int stock, 
    @RequestParam("categoria") String categoria,
    @RequestParam("imagen") MultipartFile imagen
    ) {

        ProductRequest p=new ProductRequest();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setCategoria(categoria);
        p.setImagen(imagen);

        ValidationResult result = validateProduct(p);

        if(!result.isValid()){
            return ResponseEntity.badRequest().body(result.getErrors());
        }

        try{

            Optional <Producto> producto=productoService.update(id, nombre, precio, stock, categoria, imagen);

            if(producto.isPresent()){

                return ResponseEntity.status(HttpStatus.CREATED).body(producto.orElseThrow());
            }

        }catch(Exception e){

             return ResponseEntity.badRequest().body(e.getMessage());
        }

        
        return ResponseEntity.notFound().build();
    }





    private ValidationResult validateProduct(ProductRequest product) {
    // Realizar validaciones sobre el objeto ProductRequest
    // Puedes implementar tus propias lógicas de validación aquí
    // Por ejemplo, validar que los campos obligatorios no estén vacíos
    ValidationResult validationResult = new ValidationResult();
    if (product.getNombre() == null || product.getNombre().isEmpty()) {
        validationResult.addError("nombre", "El nombre del producto es obligatorio");
    }
    // Agregar más validaciones según sea necesario
    
    return validationResult;
}



}
