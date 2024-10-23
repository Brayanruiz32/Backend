package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Producto;
import com.backend.entities.Transaccion;
import com.backend.repositories.ProductoRepository;
import com.backend.repositories.TransaccionRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransaccionServiceImpl implements IService<Transaccion> {
    
    private TransaccionRepository transaccionRepository;
    private ProductoRepository productoRepository;
    
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
    //    validarCantidad(data);
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

    public void validarCantidad(Transaccion data){
        Integer productoStock = data.getProducto().getStock();
        Integer cantidadTrasladada = data.getCantidad();
        if (cantidadTrasladada > productoStock) {
            throw new RuntimeException("La cantidad a trasladar es mayor al stock de producto");
        }
        Integer nuevoStock = productoStock - cantidadTrasladada;
        Producto producto = data.getProducto();
        producto.setStock(nuevoStock);
        productoRepository.save(producto);
    }

    
}
