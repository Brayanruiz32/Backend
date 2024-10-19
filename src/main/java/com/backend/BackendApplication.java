package com.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.backend.entities.Rol;
import com.backend.entities.Usuario;
import com.backend.services.impl.UsuarioServiceImpl;

@SpringBootApplication
public class BackendApplication implements  CommandLineRunner {

	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setUsuario("marcelo");
		nuevoUsuario.setContrasena("1234");
		nuevoUsuario.setRol(Rol.ADMINISTRADOR);
		usuarioServiceImpl.crear(nuevoUsuario);
	}

	



}
