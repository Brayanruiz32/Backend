package com.backend.config;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
        .cors(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(http -> {
            http.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();

            http.requestMatchers(HttpMethod.POST, "/autenticacion/login").permitAll();
            http.requestMatchers(HttpMethod.POST, "/usuario/create").permitAll();

            String[] paths = {"/venta/**", "/producto/**", "/compra/**", "/caja/**"};
            String[] roles = {"ADMINISTRADOR", "VENDEDOR"};
            for (String path : paths) {
                http.requestMatchers(HttpMethod.GET, path).hasAnyRole(roles);
                http.requestMatchers(HttpMethod.POST, path).hasAnyRole(roles);
                http.requestMatchers(HttpMethod.PUT, path).hasAnyRole(roles);
                http.requestMatchers(HttpMethod.DELETE, path).hasAnyRole(roles);
            }
            http.anyRequest().hasAnyRole("ADMINISTRADOR", "VENDEDOR");
        })
        .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); 
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type","*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
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
