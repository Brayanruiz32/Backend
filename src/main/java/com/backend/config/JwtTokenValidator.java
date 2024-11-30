package com.backend.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    //@Override
    /*
     * protected void doFilterInternal(HttpServletRequest request,
     * HttpServletResponse response, FilterChain filterChain)
     * throws ServletException, IOException {
     * 
     * //chapo las cabeceras de tipo authorization
     * String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
     * 
     * if (jwtToken != null) {
     * 
     * jwtToken = jwtToken.substring(7);
     * 
     * DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
     * 
     * String username = jwtUtils.extractUsuario(decodedJWT);
     * 
     * String rol = jwtUtils.getEspecificClaim(decodedJWT, "rol").asString();
     * 
     * Collection<? extends GrantedAuthority> roles =
     * AuthorityUtils.commaSeparatedStringToAuthorityList(rol);
     * 
     * SecurityContext context = SecurityContextHolder.createEmptyContext();
     * 
     * Authentication authenticationToken = new
     * UsernamePasswordAuthenticationToken(username, null, roles);
     * 
     * context.setAuthentication(authenticationToken);
     * 
     * SecurityContextHolder.setContext(context);
     * 
     * }
     * 
     * filterChain.doFilter(request, response);
     * 
     * }
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Rutas públicas que no requieren validación del token
        String requestPath = request.getServletPath();
        if (requestPath.equals("/autenticacion/login") || requestPath.equals("/usuario/create")) {
            filterChain.doFilter(request, response);
            return; // No procesar JWT en estas rutas
        }

        // Procesar el token JWT
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7); // Remover el prefijo "Bearer "

            try {
                // Validar el token JWT
                DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

                String username = jwtUtils.extractUsuario(decodedJWT);
                String rol = jwtUtils.getEspecificClaim(decodedJWT, "rol").asString();

                Collection<? extends GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(rol);

                // Crear el contexto de seguridad
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, roles);
                context.setAuthentication(authenticationToken);

                SecurityContextHolder.setContext(context);
            } catch (Exception e) {
                // Manejar errores en la validación del token (opcional)
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

}
