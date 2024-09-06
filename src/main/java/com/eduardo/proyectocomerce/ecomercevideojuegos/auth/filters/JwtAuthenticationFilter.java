package com.eduardo.proyectocomerce.ecomercevideojuegos.auth.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eduardo.proyectocomerce.ecomercevideojuegos.models.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.eduardo.proyectocomerce.ecomercevideojuegos.auth.TokenJWTConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{


    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        

                User user=null;
                String username=null;
                String password=null;


                try {
                    user=new ObjectMapper().readValue(request.getInputStream(), User.class);

                    username=user.getUsuario();
                    password=user.getContra();

                    
                } 
                catch(StreamReadException e) {
                    e.printStackTrace();
                }
                catch(DatabindException e){
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, password);
                return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        

                String username=((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

                Collection<? extends GrantedAuthority> roles=  authResult.getAuthorities();  //esto hereda de GrandAuthorities que es donde pusimos los roles
                
              boolean isAdmin=roles.stream().anyMatch(r-> r.getAuthority().equals("ROLE_ADMIN"));
              ClaimsBuilder claims=Jwts.claims();
              
              claims.add("authorities", new ObjectMapper().writeValueAsString(roles));//de esta manera pasamos los roles claims
              claims.add("isAdmin", isAdmin);
              
              // String token=Base64.getEncoder().encodeToString(originalInput.getBytes());//recive arreglo de bites y crea un toke ficticio

                String token=Jwts.builder().
                claims(claims.build()).
                subject(username).
                signWith(SECRET_KEY).
                issuedAt(new Date()).
                expiration(new Date(new Date().getTime()+3600000)).
                compact();
        

                response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN+token);//Bearer esta más relacionada al uso del token
    
    
                Map<String,Object> body=new HashMap<>();
    
                body.put("token",token);
                body.put("message",String.format("Hola %s has iniciado sesion con exito",username));//el format sirve para concatenar
                body.put("username",username);


                response.getWriter().write(new ObjectMapper().writeValueAsString(body));//esto convierte al map en un json
                response.setStatus(200);//refiere que todo salio bien
                response.setContentType("application/json");
                
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
                Map<String,Object> body=new HashMap<>();

                body.put("message","Error en la autenticación, username o password incorrecto");
                body.put("error",failed.getMessage());

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(401);
                response.setContentType("application/json");
    }


    
    
}
