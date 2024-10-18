package com.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Categoria;
import com.backend.repositories.CategoriaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class CategoriaServiceImpl implements IService<Categoria>{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria encontrar(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Categoria> encontrarTodo() {
        return categoriaRepository.findAll();
    }
    
    @Override
    public Categoria crear(Categoria data) {
        return categoriaRepository.save(data);
    }

    @Override
    public Categoria actualizar(Long id, Categoria data) {
        Categoria categoriaActualizar = encontrar(id);
        categoriaActualizar.setNombre(data.getNombre());
        return categoriaRepository.save(categoriaActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoriaEliminar = encontrar(id);
        categoriaRepository.delete(categoriaEliminar);
    }
    
}
