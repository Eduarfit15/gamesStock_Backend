package com.eduardo.proyectocomerce.ecomercevideojuegos.auth.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.eduardo.proyectocomerce.ecomercevideojuegos.auth.SimpleGrantedAuthorityJsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.eduardo.proyectocomerce.ecomercevideojuegos.auth.TokenJWTConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter{


    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
            String header=request.getHeader(HEADER_AUTHORIZATION);

        if(header==null || !header.startsWith(PREFIX_TOKEN)){
            //Valida token
            chain.doFilter(request, response);
            return;
        }

        String token=header.replace(PREFIX_TOKEN,"");
       
        try {
            
           Claims claims= Jwts.parser().
           verifyWith(SECRET_KEY).
           build().
           parseSignedClaims(token).
           getPayload();



           Object authoritiesClaims=claims.get("authorities");


           String username=claims.getSubject(); //de esta manera regresa el string del username


           //Object username2=claims.get("username"); esta es otra forma, solo poniendole otro add a los claims

           //Con esto pasamos de tipo Json a GrantedAuthority
           Collection<? extends GrantedAuthority> authorities=
            Arrays.asList
            (new ObjectMapper()
            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
            .readValue(authoritiesClaims.toString().getBytes(),SimpleGrantedAuthority[].class));



            UsernamePasswordAuthenticationToken authentication=
            new UsernamePasswordAuthenticationToken(username,null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
       
        }catch(JwtException e){

            Map<String,String> body=new HashMap<>();

            body.put("error", e.getMessage());
            body.put("message", "El token JWT no es valido");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(401); //no tiene autoriazacion
            response.setContentType("aplication/json");




        }

    }
    
    

}
