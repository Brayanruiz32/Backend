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

import com.backend.entities.Almacen;
import com.backend.services.impl.AlmacenServiceImpl;

@RestController
@RequestMapping("/almacen")
public class AlmacenController {

    @Autowired
    private AlmacenServiceImpl almacenService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Almacen> find(@PathVariable Long id){
        return new ResponseEntity<>(almacenService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Almacen>> findAll(){
        return new ResponseEntity<>(almacenService.encontrarTodo(), HttpStatus.OK);
    }

    @GetMapping("/listar/{almacenId}")
    public ResponseEntity<List<Almacen>> listarAlmacenesNoSeleccionados(@PathVariable Long almacenId){
        return new ResponseEntity<>(almacenService.listarAlmacenesNoSeleccionados(almacenId), HttpStatus.OK);
    } 

    @PostMapping("/create")
    public ResponseEntity<Almacen> create(@RequestBody Almacen almacen){
        return new ResponseEntity<>(almacenService.crear(almacen), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Almacen> update(@PathVariable Long id, @RequestBody Almacen almacen){
        return new ResponseEntity<>(almacenService.actualizar(id, almacen), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        almacenService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
