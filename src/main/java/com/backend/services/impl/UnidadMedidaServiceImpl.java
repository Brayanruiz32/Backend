package com.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.UnidadMedida;
import com.backend.repositories.UnidadMedidaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UnidadMedidaServiceImpl implements IService<UnidadMedida> {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;
    
    @Override
    public UnidadMedida encontrar(Long id) {
        return unidadMedidaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<UnidadMedida> encontrarTodo() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public UnidadMedida crear(UnidadMedida data) {
        return unidadMedidaRepository.save(data);
    }

    @Override
    public UnidadMedida actualizar(Long id, UnidadMedida data) {
        UnidadMedida unidadMedidaActualizar = encontrar(id);
        unidadMedidaActualizar.setAbreviatura(data.getAbreviatura());
        unidadMedidaActualizar.setDescripcion(data.getDescripcion());
        unidadMedidaActualizar.setNombre(data.getNombre());
        return unidadMedidaRepository.save(unidadMedidaActualizar);
    }

    @Override
    public void eliminar(Long id) {
        UnidadMedida unidadMedidaEliminar = encontrar(id);
        unidadMedidaRepository.delete(unidadMedidaEliminar);
    }
  
}
