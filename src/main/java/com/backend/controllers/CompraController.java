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

import com.backend.entities.Compra;
import com.backend.services.impl.CompraServiceImpl;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraServiceImpl compraService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Compra> find(@PathVariable Long id){
        return new ResponseEntity<>(compraService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Compra>> findAll(){
        return new ResponseEntity<>(compraService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Compra> create(@RequestBody Compra compra){
        return new ResponseEntity<>(compraService.crear(compra), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Compra> update(@PathVariable Long id, @RequestBody Compra compra){
        return new ResponseEntity<>(compraService.actualizar(id, compra), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        compraService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
