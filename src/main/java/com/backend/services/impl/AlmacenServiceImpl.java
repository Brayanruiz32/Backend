package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Almacen;
import com.backend.repositories.AlmacenRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlmacenServiceImpl implements IService<Almacen>{
    
    private AlmacenRepository almacenRepository;
    
    @Override
    public Almacen encontrar(Long id) {
        return almacenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Almacen> encontrarTodo() {
        return almacenRepository.findAll();
    }
    
    @Override
    public Almacen crear(Almacen data) {
        return almacenRepository.save(data);
    }

    @Override
    public Almacen actualizar(Long id, Almacen data) {
        Almacen almacenActualizar = this.encontrar(id);
        almacenActualizar.setNombre(data.getNombre());
        almacenActualizar.setUbicacion(data.getUbicacion());
        return almacenRepository.save(almacenActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Almacen almacenEliminar = this.encontrar(id);
        almacenRepository.delete(almacenEliminar);
    }
}
