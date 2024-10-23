package com.backend.services.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Caja;
import com.backend.entities.Estado;
import com.backend.repositories.CajaRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CajaServiceImpl implements IService<Caja> {

    @Autowired
    private CajaRepository cajaRepository;

    // // private Double montoActual;

    @Override
    public Caja actualizar(Long id, Caja data) {
        return null;
    }
    @Override
    public Caja crear(Caja data) {
        return null;
    }
    @Override
    public void eliminar(Long id) {
        cajaRepository.deleteById(id);
    }
    //endpoint
    public List<Caja> encontrarCajasPorVendedor(Long usuarioId){
        return cajaRepository.encuentraCajasPorVendedor(usuarioId);
    }

    @Override
    public Caja encontrar(Long id) {
        return cajaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Caja> encontrarTodo() {
        return cajaRepository.findAll();
    }

    //debe enviar fecha apertura y saldo inicial 
    public Caja abrirCaja(Caja caja) {
        // this.montoActual = caja.getSaldoInicial();
        Double saldoInicial = caja.getSaldoInicial();
        caja.setSaldoActual(saldoInicial);
        caja.setEstado(Estado.ABIERTA);
        return cajaRepository.save(caja);
    }

    //debe enviar fecha cierre, el saldo final lo haré la logica
    public Caja cerrarCaja(LocalDateTime fechaCierre){
        Caja caja = verificarCajaAbierta();
        caja.setFechaCierre(fechaCierre);
        caja.setSaldoFinal(caja.getSaldoActual());
        caja.setEstado(Estado.CERRADA);
        // caja.setSaldoFinal(montoActual);
        return cajaRepository.save(caja);
    }

    //endpoint
    public Caja verificarCajaAbierta() {
        return cajaRepository.findFirstByEstadoOrderByIdDesc(Estado.ABIERTA).orElseThrow(() -> new EntityNotFoundException("La caja no está abierta"));
    }

    // public boolean compraRestarSaldo(Double monto){
    //     // if (this.montoActual > monto) {
    //         // this.montoActual = this.montoActual - monto;
    //         return true;
    //     }else{
    //         return false;
    //     }
    // }

    // public void ventaSumarSaldo(Double monto){
    //     // // this.montoActual = this.montoActual + monto;
    // }
}
