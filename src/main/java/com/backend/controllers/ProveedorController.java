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

import com.backend.entities.Proveedor;
import com.backend.services.IService;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private IService<Proveedor> proveedorService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Proveedor> find(@PathVariable Long id){
        return new ResponseEntity<>(proveedorService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Proveedor>> findAll(){
        return new ResponseEntity<>(proveedorService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.crear(proveedor), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor){
        return new ResponseEntity<>(proveedorService.actualizar(id, proveedor), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        proveedorService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
