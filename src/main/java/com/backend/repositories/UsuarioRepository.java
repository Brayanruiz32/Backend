package com.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


    Optional<Usuario> findByUsuario(String usuario);

}
