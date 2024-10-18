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

import com.backend.entities.Producto;
import com.backend.services.IService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IService<Producto> productoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Producto> find(@PathVariable Long id){
        return new ResponseEntity<>(productoService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Producto>> findAll(){
        return new ResponseEntity<>(productoService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Producto> create(@RequestBody Producto producto){
        return new ResponseEntity<>(productoService.crear(producto), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto){
        return new ResponseEntity<>(productoService.actualizar(id, producto), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
