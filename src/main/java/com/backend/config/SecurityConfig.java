package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.backend.services.impl.UsuarioServiceImpl;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        
        .authorizeHttpRequests(http -> {
            // http.requestMatchers(HttpMethod.GET, "swagger-ui.html").permitAll();
            // http.requestMatchers(HttpMethod.POST, "/usuario/login").permitAll();
            http.requestMatchers(HttpMethod.DELETE, "/**").permitAll();
            http.requestMatchers(HttpMethod.GET, "/**").permitAll();
            http.requestMatchers(HttpMethod.POST, "/**").permitAll();
            http.requestMatchers(HttpMethod.PUT, "/**").permitAll();


            // String[] paths = {"/categoria/**", "/producto/**", "/usuario/**", "/rol/**", "/venta/**"};
            // String[] roles = {"ADMINISTRATIVO", "DESARROLLADOR"};
            // for (String path : paths) {
            //     http.requestMatchers(HttpMethod.GET, path).hasAnyRole("CLIENTE","INVITADO");
            //     http.requestMatchers(HttpMethod.POST, path).hasAnyRole(roles);
            //     http.requestMatchers(HttpMethod.PUT, path).hasAnyRole(roles);
            //     http.requestMatchers(HttpMethod.DELETE, path).hasAnyRole(roles);
            // }
           
        })
        .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(UsuarioServiceImpl usuarioServiceImpl){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());

        provider.setUserDetailsService(usuarioServiceImpl);
   
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
