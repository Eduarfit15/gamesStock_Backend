package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.UserRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.UserDto;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.dto.mapper.DtoMapperUser;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Role;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.RoleRepository;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.UserRepository;

@Service
public class UserService implements IUserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

     @Autowired
    private PasswordEncoder passwordEncoder; 



    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
       
        List<User> users=(List<User>) userRepository.findAll();

        return users
        .stream().map(u-> DtoMapperUser.builder().setUser(u).build())
        .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Optional<UserDto> findByUsuario(String username) {
        
        return userRepository.findUsuario(username).map(u->
        DtoMapperUser.builder().setUser(u).build());
    }


    @Override
    @Transactional
    public UserDto saveUser(User user) {
        
        String passwordBc=passwordEncoder.encode(user.getContra());
        user.setContra(passwordBc);

        List<Role> roles=new ArrayList<Role>();
        roles=getRoles("ROLE_USER");
        user.setRoles(roles);

        return DtoMapperUser.builder().setUser(userRepository.save(user)).build();

    }
  
    @Override
    @Transactional
    public UserDto saveAdmin(User user) {
        
        String passwordBc=passwordEncoder.encode(user.getContra());
        user.setContra(passwordBc);


        List<Role> roles=new ArrayList<Role>();
        roles=getRoles("ROLE_ADMIN");
        user.setRoles(roles);

        return DtoMapperUser.builder().setUser(userRepository.save(user)).build();

        
    }




    private List<Role> getRoles(String rol){


        Optional<Role> ou=roleRepository.findByName(rol);
        List<Role> roles=new ArrayList<>();

        if(ou.isPresent()){
            roles.add(ou.orElseThrow());
        }

        return roles;



    }

    @Override
    @Transactional
    public Optional<UserDto> update(UserRequest user, String username) {
       
        Optional<User> o=userRepository.findUsuario(username);

        if(o.isPresent()){
            User userBd=o.orElseThrow();
            userBd.setUsuario(user.getUsuario());
            userBd.setNombre(user.getNombre());
            userBd.setApellido(user.getApellido());
            userBd.setCorreo(user.getCorreo());
            userBd.setNroCelular(user.getNroCelular());

            return Optional.of(DtoMapperUser.builder().setUser(userRepository.save(userBd)).build());
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public void remove(String username) {

        userRepository.deleteByUsuario(username);
    }

    @Override
    public Optional<UserDto> updatePass(String username, String password) {
        

        Optional<User> o=userRepository.findUsuario(username);

        if(o.isPresent()){
            String passwordBc=passwordEncoder.encode(password);
            User userBd=o.orElseThrow();
            userBd.setContra(passwordBc);

            return Optional.of(DtoMapperUser.builder().setUser(userRepository.save(userBd)).build());
            
    }
    return Optional.empty();

    }

    @Override
    public Optional<UserDto> findByUsuariowROL(String username) {
        return userRepository.findUsuario(username).map(u->
        DtoMapperUser.builder().setUser(u).build());
    }

   
    
    
}
