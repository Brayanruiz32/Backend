package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Caja;
import com.backend.entities.DetalleCompra;
import com.backend.entities.DetalleVenta;
import com.backend.entities.Producto;
import com.backend.entities.Venta;
import com.backend.repositories.CajaRepository;
import com.backend.repositories.DetalleCompraRepository;
import com.backend.repositories.ProductoRepository;
import com.backend.repositories.VentaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VentaServiceImpl implements IService<Venta> {
    
    private final VentaRepository ventaRepository;
    private ProductoServiceImpl productoServiceImpl;
    private final ProductoRepository productoRepository;
    private DetalleCompraRepository detalleCompraRepository;
    private CajaServiceImpl cajaServiceImpl;
    private CajaRepository cajaRepository;

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
        //validar apertura de caja
        Caja cajaAbierta = cajaServiceImpl.verificarCajaAbierta();
        if (cajaAbierta == null) {
            throw new RuntimeException("La caja no se encuentra abierta");
        }
        
        Venta nuevaVenta = new Venta();
        List<DetalleVenta> detallesVentas = data.getDetalleVentas();       
        double total = detallesVentas.stream().mapToDouble(DetalleVenta::getSubtotal).sum();

        double saldoActual = cajaAbierta.getSaldoActual() + total;
        cajaAbierta.setSaldoActual(saldoActual);
        
        //mermar el stock
        for (DetalleVenta detalleVenta : detallesVentas) {
            Producto producto = productoServiceImpl.encontrar(detalleVenta.getProducto().getId());
            Integer stock = producto.getStock() - detalleVenta.getCantidad();
            if (stock <= 0) {
                throw new RuntimeException("Error, no hay suficiente stock");
            }
            producto.setStock(stock);
            productoRepository.save(producto);
        }
        
        cajaRepository.save(cajaAbierta);
        //setear valores
        nuevaVenta.setDetalleVentas(detallesVentas);
        nuevaVenta.setTotal(total);
        nuevaVenta.setFechaVenta(data.getFechaVenta());
        nuevaVenta.setNumeroDocumento(data.getNumeroDocumento());
        nuevaVenta.setTipoComprobante(data.getTipoComprobante());
        nuevaVenta.setTipoDocumento(data.getTipoDocumento());
        nuevaVenta.setTipoPago(data.getTipoPago());
        //se debe sumar a la caja el total 
        return ventaRepository.save(nuevaVenta);
    }
    
    @Override
    public Venta actualizar(Long id, Venta data) {
        Venta ventaActualizar = this.encontrar(id);

        List<DetalleVenta> detallesVentas = data.getDetalleVentas();       
        double total = detallesVentas.stream().mapToDouble(DetalleVenta::getSubtotal).sum();

        ventaActualizar.setDetalleVentas(detallesVentas);
        ventaActualizar.setTotal(total);
        ventaActualizar.setFechaVenta(data.getFechaVenta());
        ventaActualizar.setNumeroDocumento(data.getNumeroDocumento());
        ventaActualizar.setTipoComprobante(data.getTipoComprobante());
        ventaActualizar.setTipoDocumento(data.getTipoDocumento());
        ventaActualizar.setTipoPago(data.getTipoPago());
        
        return ventaRepository.save(ventaActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Venta ventaEliminar = this.encontrar(id);
        ventaRepository.delete(ventaEliminar);
    }

    //obtener precio de venta (va ser un endpoint)
    public Double obtenerPrecioVentaPorId(Long productoId){
        DetalleCompra precioCompra = detalleCompraRepository.findTopByProductoIdOrderByIdDesc(productoId);
        Double precioVenta = precioCompra.getPrecioCompra() * 1.15;
        return precioVenta;
        // return null;
    }
}
