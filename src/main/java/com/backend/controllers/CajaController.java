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

import com.backend.entities.Caja;
import com.backend.services.impl.CajaServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/caja")
public class CajaController {

    @Autowired
    private CajaServiceImpl cajaServiceImpl;

    @GetMapping("/find/{id}")
    public ResponseEntity<Caja> find(@PathVariable Long id){
        return new ResponseEntity<>(cajaServiceImpl.encontrar(id), HttpStatus.OK);
    }


    //aqui envia fecha_apertura y saldo inicial
    @PostMapping("/abrir")
    public ResponseEntity<Caja> abrirCaja(@RequestBody Caja caja){
        return new ResponseEntity<>(cajaServiceImpl.abrirCaja(caja), HttpStatus.OK);
    }

    //aqui envia fecha_cierre y saldo final 
    @PutMapping("/cerrar")
    public ResponseEntity<Caja> cerrarCaja(@RequestBody Caja caja){
        return new ResponseEntity<>(cajaServiceImpl.cerrarCaja(caja.getFechaCierre()), HttpStatus.OK);
    }

    @GetMapping("/verificarCajaAbierta")
    public ResponseEntity<?> verificar() {
        try {
            Caja caja = cajaServiceImpl.verificarCajaAbierta();
            return new ResponseEntity<>(caja, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La caja no esta abierta", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<List<Caja>> verificar(@PathVariable Long id) {
        return new ResponseEntity<>(cajaServiceImpl.encontrarCajasPorVendedor(id), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Caja>> listarCajasAdmin() {
        return new ResponseEntity<>(cajaServiceImpl.encontrarTodo(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarCaja(@PathVariable Long id){
        cajaServiceImpl.eliminar(id);
        return "se eliminó correctamente la caja";
    }
}
