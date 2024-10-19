package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Transaccion;
import com.backend.repositories.TransaccionRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransaccionServiceImpl implements IService<Transaccion> {
    
    private TransaccionRepository transaccionRepository;
    
    @Override
    public Transaccion encontrar(Long id) {
        return transaccionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Transaccion> encontrarTodo() {
        return transaccionRepository.findAll();
    }
    
    @Override
    public Transaccion crear(Transaccion data) {
        return transaccionRepository.save(data);
    }

    @Override
    public Transaccion actualizar(Long id, Transaccion data) {
        Transaccion transaccionActualizar = this.encontrar(id);
        transaccionActualizar.setCantidad(data.getCantidad());
        transaccionActualizar.setFechaTransaccion(data.getFechaTransaccion());
        transaccionActualizar.setAlmacenDestino(data.getAlmacenDestino());
        transaccionActualizar.setAlmacenOrigen(data.getAlmacenOrigen());
        transaccionActualizar.setProducto(data.getProducto());
        return transaccionRepository.save(transaccionActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Transaccion transaccionEliminar = this.encontrar(id);
        transaccionRepository.delete(transaccionEliminar);
    }
}
