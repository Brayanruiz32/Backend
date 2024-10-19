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

import com.backend.entities.Transaccion;
import com.backend.services.IService;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private IService<Transaccion> transaccionService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaccion> find(@PathVariable Long id){
        return new ResponseEntity<>(transaccionService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Transaccion>> findAll(){
        return new ResponseEntity<>(transaccionService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Transaccion> create(@RequestBody Transaccion transaccion){
        return new ResponseEntity<>(transaccionService.crear(transaccion), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Transaccion> update(@PathVariable Long id, @RequestBody Transaccion transaccion){
        return new ResponseEntity<>(transaccionService.actualizar(id, transaccion), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transaccionService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
