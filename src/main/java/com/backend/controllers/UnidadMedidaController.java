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

import com.backend.entities.UnidadMedida;
import com.backend.services.IService;

@RestController
@RequestMapping("/unidadmedida")
public class UnidadMedidaController {

    @Autowired
    private IService<UnidadMedida> unidadMedidaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<UnidadMedida> find(@PathVariable Long id){
        return new ResponseEntity<>(unidadMedidaService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<UnidadMedida>> findAll(){
        return new ResponseEntity<>(unidadMedidaService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UnidadMedida> create(@RequestBody UnidadMedida unidadMedida){
        return new ResponseEntity<>(unidadMedidaService.crear(unidadMedida), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UnidadMedida> update(@PathVariable Long id, @RequestBody UnidadMedida unidadMedida){
        return new ResponseEntity<>(unidadMedidaService.actualizar(id, unidadMedida), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        unidadMedidaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
