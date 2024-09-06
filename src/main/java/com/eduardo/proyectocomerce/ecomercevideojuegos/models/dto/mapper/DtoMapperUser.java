package com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.mapper;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.UserDto;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;

public class DtoMapperUser {
    

    //patron de diseño builder

    private User user;

    private DtoMapperUser(){

    }

    public static DtoMapperUser builder(){
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User user){

        this.user=user;
        return this;
    }

    public UserDto build(){

        if(user==null){
                throw new RuntimeException("Debe para el usuario user");
        }

        boolean isAdmin=user.getRoles().stream().anyMatch(r-> "ROLE_ADMIN".equals(r.getName()));

        return new UserDto(this.user.getIDusuario()
        ,user.getUsuario()
        ,user.getNombre()
        ,user.getApellido()
        ,user.getCorreo()
        ,user.getNroCelular(),isAdmin);
    }

}
