package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.DetalleVenta;
import com.backend.entities.Venta;
import com.backend.repositories.VentaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VentaServiceImpl implements IService<Venta> {
    
    private final VentaRepository ventaRepository;


    @Override
    public List<Venta> encontrarTodo() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta encontrar(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
    
    @Override
    public Venta crear(Venta data) {
        Venta nuevaVenta = new Venta();

        List<DetalleVenta> detallesVentas = data.getDetalleVentas();       
        double total = detallesVentas.stream().mapToDouble(DetalleVenta::getSubtotal).sum();

        nuevaVenta.setDetalleVentas(detallesVentas);
        nuevaVenta.setTotal(total);
        nuevaVenta.setFecha(data.getFecha());


        return ventaRepository.save(nuevaVenta);
    }
    
    @Override
    public Venta actualizar(Long id, Venta data) {
        Venta ventaActualizar = this.encontrar(id);

        List<DetalleVenta> detallesVentas = data.getDetalleVentas();       
        double total = detallesVentas.stream().mapToDouble(DetalleVenta::getSubtotal).sum();

        ventaActualizar.setDetalleVentas(detallesVentas);
        ventaActualizar.setTotal(total);
        ventaActualizar.setFecha(data.getFecha());
        

        return ventaRepository.save(ventaActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Venta ventaEliminar = this.encontrar(id);
        ventaRepository.delete(ventaEliminar);
    }

}
