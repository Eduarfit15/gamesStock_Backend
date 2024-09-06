package com.eduardo.proyectocomerce.ecomercevideojuegos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.UserRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.UserDto;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;
import com.eduardo.proyectocomerce.ecomercevideojuegos.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLDecoder;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*")
public class UserController {
    

    @Autowired
    private UserService userService;


    @GetMapping
    public List<UserDto> list(){

        return userService.findAll();
    }


    @GetMapping("/findUser/{username}")
    public ResponseEntity<?> showwithRol(@PathVariable String username){

        try {
            String decodeUsername = URLDecoder.decode(username, "UTF-8");
            Optional<UserDto> userOptional=userService.findByUsuario(decodeUsername);
    
            System.out.println(decodeUsername);
            if(userOptional.isPresent()){
                return ResponseEntity.ok(userOptional.orElseThrow());
            }
    
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al codificar el username: " + e.getMessage());
        }
       
    }


    @GetMapping("/findUserWRol/{username}")
    public ResponseEntity<?> showWithoutRol(@PathVariable String username){

        try {
            String decodeUsername = URLDecoder.decode(username, "UTF-8");
            Optional<UserDto> userOptional=userService.findByUsuariowROL(decodeUsername);
    
            System.out.println(decodeUsername);
            if(userOptional.isPresent()){
                return ResponseEntity.ok(userOptional.orElseThrow());
            }
    
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al codificar el username: " + e.getMessage());
        }
       
    }

    @PostMapping("/admin")
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){

        if(result.hasErrors()){
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAdmin(user));
    }

    @PostMapping("/client")
    public ResponseEntity<?> createC(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        
    }


    
    @PutMapping("/{username}")
    public ResponseEntity<?> update ( @RequestBody UserRequest user, @PathVariable String username){

        
        Optional<UserDto> o =userService.update(user, username);

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());

        }


        return ResponseEntity.notFound().build();
    }


    @PutMapping("/recoverPass")
    public ResponseEntity<?> recoverPass(@RequestParam("username") String username, @RequestParam("password") String password){

    Optional<UserDto> o=userService.updatePass(username,password);

    if(o.isPresent()){
        return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    }
    return ResponseEntity.notFound().build();

    }



    @DeleteMapping("/{username}")
    public ResponseEntity<?> remove(@PathVariable String username){


        Optional<UserDto> o =userService.findByUsuario(username);

        if(o.isPresent()){

            userService.remove(username);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result){

        Map<String,String> errors=new HashMap<>();


        result.getFieldErrors().forEach(err->{

            errors.put(err.getField(), "El campo" + err.getField() + " "+ err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }


    
    
}
