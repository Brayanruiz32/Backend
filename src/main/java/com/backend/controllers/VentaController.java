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

import com.backend.entities.Venta;
import com.backend.services.impl.VentaServiceImpl;

@RestController
@RequestMapping("/venta")
public class VentaController {


    @Autowired
    private VentaServiceImpl ventaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Venta> find(@PathVariable Long id){
        return new ResponseEntity<>(ventaService.encontrar(id), HttpStatus.OK);
    }

    @GetMapping("/precio/{productoId}")
    public ResponseEntity<Double> findPrice(@PathVariable Long productoId){
        return new ResponseEntity<>(ventaService.obtenerPrecioVentaPorId(productoId), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Venta>> findAll(){
        return new ResponseEntity<>(ventaService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Venta> create(@RequestBody Venta venta){
        return new ResponseEntity<>(ventaService.crear(venta), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Venta> update(@PathVariable Long id, @RequestBody Venta venta){
        return new ResponseEntity<>(ventaService.actualizar(id, venta), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
