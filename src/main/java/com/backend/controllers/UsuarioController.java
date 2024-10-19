package com.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entities.Usuario;
import com.backend.services.IService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IService<Usuario> usuarioService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Usuario>> findAll(){
        return new ResponseEntity<>(usuarioService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.crear(usuario), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.actualizar(id, usuario), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
