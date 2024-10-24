package com.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.backend.config.JwtUtils;
import com.backend.dto.ResponseLogin;
import com.backend.dto.UsuarioLoginDTO;
import com.backend.entities.Usuario;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@RestController
@Slf4j
public class UsuarioServiceImpl implements IService<Usuario>, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public Usuario encontrar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Usuario> encontrarTodo() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crear(Usuario data) {
        Usuario usuario = data;
        usuario.setContrasena(passwordEncoder.encode(data.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario data) {
        Usuario usuarioActualizar = this.encontrar(id);
        usuarioActualizar.setApellidos(data.getApellidos());
        usuarioActualizar.setContrasena(data.getContrasena());
        usuarioActualizar.setCorreo(data.getCorreo());
        usuarioActualizar.setDireccion(data.getDireccion());
        usuarioActualizar.setNombre(data.getNombre());
        usuarioActualizar.setRol(data.getRol());
        usuarioActualizar.setTelefono(data.getTelefono());
        return usuarioRepository.save(usuarioActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuarioEliminar = this.encontrar(id);
        usuarioRepository.delete(usuarioEliminar);
    }
    
    
    
    public ResponseLogin login(UsuarioLoginDTO usuarioLoginDTO){

        String usuario = usuarioLoginDTO.getUsuario();
        String contrasenia = usuarioLoginDTO.getContrasena();

        Authentication authentication = this.authenticate(usuario, contrasenia);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);

        Usuario user = usuarioRepository.findByUsuario(usuario).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        ResponseLogin responseLogin = new ResponseLogin(usuario, user.getId(), token);

        return responseLogin;
    }
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ingresa a consultar el nombre");

        Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(usuario.getRol().toString())));
        return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), 
        usuario.getContrasena(), authorities);
    }

    private Authentication authenticate(String usuario, String contrasenia) {
        
        UserDetails userDetails = loadUserByUsername(usuario);
   
        if (userDetails == null) {
            throw new UsernameNotFoundException("No se encuentra el usuario");
        }

        if (!passwordEncoder.matches(contrasenia, userDetails.getPassword())) {
            throw new BadCredentialsException("Las credenciales son incorrectas");
        }
        return new UsernamePasswordAuthenticationToken(usuario, contrasenia, userDetails.getAuthorities());
    }


}
