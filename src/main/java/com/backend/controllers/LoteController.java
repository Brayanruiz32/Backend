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

import com.backend.entities.Lote;
import com.backend.services.impl.LoteServiceImpl;

@RestController
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    private LoteServiceImpl loteService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Lote> find(@PathVariable Long id){
        return new ResponseEntity<>(loteService.encontrar(id), HttpStatus.OK);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<Lote>> findAll(){
        return new ResponseEntity<>(loteService.encontrarTodo(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Lote> create(@RequestBody Lote lote){
        return new ResponseEntity<>(loteService.crear(lote), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Lote> update(@PathVariable Long id, @RequestBody Lote lote){
        return new ResponseEntity<>(loteService.actualizar(id, lote), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        loteService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
