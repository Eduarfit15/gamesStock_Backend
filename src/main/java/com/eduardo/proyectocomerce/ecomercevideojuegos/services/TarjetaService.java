package com.eduardo.proyectocomerce.ecomercevideojuegos.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.Tarjeta;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.TarjetaRequest;
import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.TarjetaRepository;
import com.eduardo.proyectocomerce.ecomercevideojuegos.repositories.UserRepository;

@Service
public class TarjetaService implements ITarjeta{


    @Autowired
    private TarjetaRepository tRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Tarjeta> save(Tarjeta tarjeta, String username) {
        Optional<User> user=userRepository.findUsuario(username);

        if(user.isPresent()){
            User bdUser=user.orElseThrow();
            tarjeta.setUser(bdUser);

            return Optional.of(tRepository.save(tarjeta));

        }

        return Optional.empty();
    }

    @Override
    public TarjetaRequest getTarjeta(String username) {
        
        return tRepository.getTarjeta(username);
    }

    @Override
    public void deleteTarjeta(String username) {
        

        Tarjeta tar=tRepository.searchTarjeta(username);

        if(tar!=null){
            tRepository.delete(tar);
        }
    }

   
    

    



}
