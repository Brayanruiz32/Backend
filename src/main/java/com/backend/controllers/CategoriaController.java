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

import com.backend.entities.Categoria;
import com.backend.services.IService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private IService<Categoria> categoriaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id){
        return new ResponseEntity<>(categoriaService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Categoria>> findAll(){
        return new ResponseEntity<>(categoriaService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.crear(categoria), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.actualizar(id, categoria), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoriaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
