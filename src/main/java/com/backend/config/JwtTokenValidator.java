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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        //chapo las cabeceras de tipo authorization 
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if (jwtToken != null) {

            jwtToken = jwtToken.substring(7);

            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String username = jwtUtils.extractUsuario(decodedJWT);

            String rol = jwtUtils.getEspecificClaim(decodedJWT, "rol").asString();

            Collection<? extends GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(rol);

            SecurityContext context = SecurityContextHolder.createEmptyContext();

            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, roles);

            context.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(context);

        }

        filterChain.doFilter(request, response);
        
    }
    
}
