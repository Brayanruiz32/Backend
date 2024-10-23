package com.backend.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.backend.entities.Caja;
import com.backend.entities.Compra;
import com.backend.entities.DetalleCompra;
import com.backend.entities.Fase;
import com.backend.entities.Lote;
import com.backend.entities.Proceso;
import com.backend.entities.Producto;
import com.backend.repositories.CajaRepository;
import com.backend.repositories.CompraRepository;
import com.backend.repositories.LoteRepository;
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
    private LoteRepository loteRepository;
    private CajaServiceImpl cajaServiceImpl;
    private CajaRepository cajaRepository;

    @Override
    public Compra encontrar(Long id) {
        return compraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
    
    //las solicitud de compra
    public List<Compra> comprasEnGestionando(){
        return compraRepository.findAllByFase(Fase.GESTIONANDO);
    }
    //las ordenes de compra
    public List<Compra> comprasEnLiquidado(){
        return compraRepository.findAllByFase(Fase.LIQUIDADO);
    }

    @Override
    public List<Compra> encontrarTodo() {
        return compraRepository.findAll();
    }

    //se efectua la compra en fase gestionando 
    @Override
    public Compra crear(Compra data) {
        data.setFase(Fase.GESTIONANDO);
        List<DetalleCompra> detalleCompras = data.getDetalleCompras();
        double total = detalleCompras.stream().mapToDouble(DetalleCompra::getSubTotal).sum(); 
        data.setTotal(total);
        return compraRepository.save(data);
    }
    
    //se liquidará la compra
    @Override
    public Compra actualizar(Long id, Compra data) {

        return null;
    }

    @Override
    public void eliminar(Long id) {
        Compra compraEliminar = this.encontrar(id);
        compraRepository.delete(compraEliminar);
    }


    public Compra liquidarCompra(Long id){
        //validar caja abierta
        Caja cajaAbierta = cajaServiceImpl.verificarCajaAbierta();
        if (cajaAbierta == null) {
            throw new RuntimeException("No hay ninguna caja abierta");
        }

        double saldoActual = cajaAbierta.getSaldoActual();
        Compra compraLiquidada = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Error al liquidar la compra"));
        compraLiquidada.setFase(Fase.LIQUIDADO);
        List<DetalleCompra> detalleCompras = compraLiquidada.getDetalleCompras();

        double total = detalleCompras.stream().mapToDouble(DetalleCompra::getSubTotal).sum();      
        if (total > saldoActual) {
            throw new RuntimeException("No hay suficiente saldo en caja");
        }else{
            saldoActual = saldoActual - total;
            cajaAbierta.setSaldoActual(saldoActual);
            cajaRepository.save(cajaAbierta);
        }
        compraLiquidada.setTotal(total);
        //aumento el stock de los productos
        Set<Long> lotesIds = new HashSet<>();
        for (DetalleCompra detalleCompra : detalleCompras) {
            lotesIds.add(detalleCompra.getLote().getId());
            Producto producto = productoServiceImpl.encontrar(detalleCompra.getProducto().getId());
            Integer stock = producto.getStock() + detalleCompra.getCantidad();
            producto.setStock(stock);
            productoRepository.save(producto);
        }
        List<Long> listaUnicaLotesIds = new ArrayList<>(lotesIds);
        actualizacionLoteACerrado(listaUnicaLotesIds);
        return compraRepository.save(compraLiquidada);
    }

    public void actualizacionLoteACerrado(List<Long> lotesids){
        for (Long long1 : lotesids) {
            Lote lote = loteRepository.findById(long1).orElseThrow(() -> new RuntimeException("Error al al buscar un lote para cerrarlo"));
            lote.setProceso(Proceso.FINALIZADO);
            loteRepository.save(lote);
        }
    }
}
