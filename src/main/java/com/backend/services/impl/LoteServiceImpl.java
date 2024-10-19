package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Lote;
import com.backend.repositories.LoteRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoteServiceImpl implements  IService<Lote>{
    
    private LoteRepository loteRepository;

    @Override
    public Lote encontrar(Long id) {
        return loteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
    
    @Override
    public List<Lote> encontrarTodo() {
        return loteRepository.findAll();
    }
    
    @Override
    public Lote crear(Lote data) {
        return loteRepository.save(data);
    }

    @Override
    public Lote actualizar(Long id, Lote data) {
        Lote loteActualizar = this.encontrar(id);
        loteActualizar.setAlmacen(data.getAlmacen());
        loteActualizar.setFechaIngreso(data.getFechaIngreso());
        loteActualizar.setFechaVencimiento(data.getFechaVencimiento());
        loteActualizar.setNumeroLote(data.getNumeroLote());


        return loteRepository.save(loteActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Lote loteEliminar = this.encontrar(id);
        loteRepository.delete(loteEliminar);
    }
}
