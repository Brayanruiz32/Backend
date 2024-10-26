package com.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entities.Categoria;
import com.backend.entities.Marca;
import com.backend.entities.Producto;
import com.backend.entities.UnidadMedida;
import com.backend.repositories.ProductoRepository;
import com.backend.services.IService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements  IService<Producto> {

    private final ProductoRepository productoRepository;
    private final MarcaServiceImpl marcaServiceImpl;
    private final CategoriaServiceImpl categoriaServiceImpl;
    private final UnidadMedidaServiceImpl unidadMedidaServiceImpl;
    
    @Override
    public Producto encontrar(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Producto> encontrarTodo() {
        return productoRepository.findAll();
    }
    
    @Override
    public Producto crear(Producto data) {
        data.setStock(0);
        return productoRepository.save(data);
    }

    @Override
    public Producto actualizar(Long id, Producto data) {
        Producto productoActualizar = this.encontrar(id);
        //validación de relaciones 
        Marca marcaProducto = marcaServiceImpl.encontrar(data.getMarca().getId());
        Categoria categoriaProducto = categoriaServiceImpl.encontrar(data.getCategoria().getId());
        UnidadMedida unidadMedidaProducto = unidadMedidaServiceImpl.encontrar(data.getUnidadMedida().getId());

        //seteo de data
        productoActualizar.setDescripcion(data.getDescripcion());
        productoActualizar.setNombre(data.getNombre());        
        productoActualizar.setCodigoDeBarras(data.getCodigoDeBarras());
        productoActualizar.setCategoria(categoriaProducto);
        productoActualizar.setMarca(marcaProducto);
        productoActualizar.setUnidadMedida(unidadMedidaProducto);

        return productoRepository.save(productoActualizar);
    }

    @Override
    public void eliminar(Long id) {
        Producto productoEliminar = this.encontrar(id);
        productoRepository.delete(productoEliminar);

    }

    public List<Producto> buscarProducto(String nombre){
        return productoRepository.findAllByName(nombre);
    }
}
