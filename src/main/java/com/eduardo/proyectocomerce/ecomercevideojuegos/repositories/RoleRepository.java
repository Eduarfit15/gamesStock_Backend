package com.eduardo.proyectocomerce.ecomercevideojuegos.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{
    

    Optional<Role> findByName(String username);

}
