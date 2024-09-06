 package com.eduardo.proyectocomerce.ecomercevideojuegos.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import com.eduardo.proyectocomerce.ecomercevideojuegos.auth.filters.JwtValidationFilter;
import com.eduardo.proyectocomerce.ecomercevideojuegos.auth.filters.JwtAuthenticationFilter;





@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http.authorizeHttpRequests(authRules -> authRules
                        .requestMatchers(HttpMethod.GET,"/products/NoStateProducts").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/admin").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/client").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/users/recoverPass").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/findUserWRol/{username}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/venta/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/venta/compras/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/users/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/users/findUser/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/tarjeta/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/tarjeta/getCard/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/tarjeta/deCard/{username}").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/products/{estado}/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/products/crear").hasRole("ADMIN")
                        .requestMatchers("/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .build();
    }




    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){


        //Con esto configuramos los cors para que todo tipo de metodo sea aceptado en el pueto 5173
        CorsConfiguration config=new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @SuppressWarnings("null")
    @Bean
    FilterRegistrationBean<CorsFilter> corsfilter(){

        //Le damos de esta maenra filtro con priroridad para que se pueda ejecutar bien la aplicacion
        FilterRegistrationBean<CorsFilter> bean=new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); //para darle la mayor prioridad

        return bean;
    }
}
 