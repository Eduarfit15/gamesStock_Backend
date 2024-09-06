package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.UserRepository;


@Service
@Transactional(readOnly=true)
public class JpaUserDeatilsService implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Optional<com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User> o=userRepository.findByUsuario(username);

        if(!o.isPresent()){

            throw new UsernameNotFoundException(String.format("Username no existe en el sistema",username));
        }

        com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User user=o.orElseThrow();

        List<GrantedAuthority> authorities =user.getRoles().
        stream().
        map(r-> new SimpleGrantedAuthority(r.getName()))
        .collect(Collectors.toList());

        //De esta manera obtenemos lo que sono los roles de los usuarios usando diferentes atajos de java


            return new User(user.getUsuario(),user.getContra(),
            true,
            true,
            true,
            true,
            authorities);
                }


    
    
}
