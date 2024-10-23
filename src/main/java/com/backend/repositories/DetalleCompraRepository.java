package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.DetalleCompra;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long>{

    DetalleCompra findTopByProductoIdOrderByIdDesc(Long productoId);

}
