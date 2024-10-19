package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Compra;
import com.backend.repositories.CompraRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraServiceImpl implements IService<Compra> {
    
    private CompraRepository compraRepository;
    
    @Override
    public Compra encontrar(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Compra> encontrarTodo() {
        return compraRepository.findAll();
    }

    @Override
    public Compra crear(Compra data) {
        return compraRepository.save(data);
    }

    @Override
    public Compra actualizar(Long id, Compra data) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Compra compraEliminar = this.encontrar(id);
        compraRepository.delete(compraEliminar);
    }
}
