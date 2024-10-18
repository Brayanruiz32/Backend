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

import com.backend.entities.Marca;
import com.backend.services.IService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private IService<Marca> marcaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Marca> find(@PathVariable Long id){
        return new ResponseEntity<>(marcaService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Marca>> findAll(){
        return new ResponseEntity<>(marcaService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Marca> create(@RequestBody Marca marca){
        return new ResponseEntity<>(marcaService.crear(marca), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Marca> update(@PathVariable Long id, @RequestBody Marca Marca){
        return new ResponseEntity<>(marcaService.actualizar(id, Marca), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        marcaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
