package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.DetalleVenta;
import com.backend.repositories.DetalleVentaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetalleVentaServiceImpl implements IService<DetalleVenta> {

    private final DetalleVentaRepository detalleVentaRepository;
    // private final ProductoServiceImpl productoServiceImpl;

    @Override
    public DetalleVenta encontrar(Long id) {
        return detalleVentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<DetalleVenta> encontrarTodo() {
        return detalleVentaRepository.findAll();
    }
    
    @Override
    public DetalleVenta crear(DetalleVenta data) {
        // Producto productoDetalle = productoServiceImpl.encontrar(data.getProducto().getId());
        return null;
    }

    @Override
    public DetalleVenta actualizar(Long id, DetalleVenta data) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        
    }
}
