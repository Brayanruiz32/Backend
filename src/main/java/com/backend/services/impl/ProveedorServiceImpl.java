package com.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Proveedor;
import com.backend.repositories.ProveedorRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProveedorServiceImpl implements IService<Proveedor> {

    @Autowired
    private ProveedorRepository proveedorRepository;
    
    @Override
    public Proveedor encontrar(Long id) {
        return proveedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Proveedor> encontrarTodo() {
        return proveedorRepository.findAll();
    }
    
    @Override
    public Proveedor crear(Proveedor data) {
        return proveedorRepository.save(data);
    }

    @Override
    public Proveedor actualizar(Long id, Proveedor data) {
        Proveedor proveedorActualizar = proveedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        proveedorActualizar.setNombre(data.getNombre());
        proveedorActualizar.setDireccion(data.getDireccion());
        proveedorActualizar.setEmail(data.getEmail());
        proveedorActualizar.setNombreComercial(data.getNombreComercial());
        proveedorActualizar.setRazonSocial(data.getRazonSocial());
        proveedorActualizar.setTipoDocumento(data.getTipoDocumento());
        proveedorActualizar.setTelefono(data.getTelefono());
        return proveedorRepository.save(proveedorActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Proveedor proveedorEliminar = proveedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        proveedorRepository.delete(proveedorEliminar);
    }
}
