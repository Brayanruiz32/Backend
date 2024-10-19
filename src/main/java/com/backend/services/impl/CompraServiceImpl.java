package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Compra;
import com.backend.entities.DetalleCompra;
import com.backend.entities.Producto;
import com.backend.repositories.CompraRepository;
import com.backend.repositories.ProductoRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraServiceImpl implements IService<Compra> {
    
    private CompraRepository compraRepository;
    private ProductoServiceImpl productoServiceImpl;
    private ProductoRepository productoRepository;
    
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
        List<DetalleCompra> detalleCompras = data.getDetalleCompras();

        for (DetalleCompra detalleCompra : detalleCompras) {
            Producto producto = productoServiceImpl.encontrar(detalleCompra.getProducto().getId());
            Integer stock = producto.getStock() + detalleCompra.getCantidad();
            producto.setStock(stock);
            productoRepository.save(producto);
        }
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
