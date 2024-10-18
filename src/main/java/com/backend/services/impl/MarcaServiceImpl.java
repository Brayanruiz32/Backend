package com.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Marca;
import com.backend.repositories.MarcaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements IService<Marca> {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public Marca encontrar(Long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Marca> encontrarTodo() {
        return marcaRepository.findAll();
    }
    
    @Override
    public Marca crear(Marca data) {
        return marcaRepository.save(data);
    }

    @Override
    public Marca actualizar(Long id, Marca data) {
        Marca marcaActualizar = marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        marcaActualizar.setNombre(data.getNombre());
        return marcaRepository.save(marcaActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Marca marcaEliminar = marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        marcaRepository.delete(marcaEliminar);
    }
    
}
