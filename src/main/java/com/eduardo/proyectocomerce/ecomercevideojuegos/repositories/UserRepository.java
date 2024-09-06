package com.eduardo.proyectocomerce.ecomercevideojuegos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;

public interface UserRepository extends CrudRepository<User,Long>{


    @Query(value = "select * from users where usuario=?1",nativeQuery = true)
    Optional<User> findUsuario(String username);

    User deleteByUsuario(String username);

     Optional<User> findByUsuario(String username); 
        
}
