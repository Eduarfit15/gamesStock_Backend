package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.List;
import java.util.Optional;



import com.eduardo.proyectocomerce.ecomercevideojuegos.models.UserRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.UserDto;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;

public interface IUserService {
    

    List<UserDto> findAll();
    Optional<UserDto> findByUsuario(String username);

    Optional<UserDto> findByUsuariowROL(String username);
    UserDto saveUser(User user);
    UserDto saveAdmin(User user);
    Optional<UserDto> update(UserRequest user, String username);
    void remove(String username);

    Optional<UserDto> updatePass(String username,String password);

  



    


}
