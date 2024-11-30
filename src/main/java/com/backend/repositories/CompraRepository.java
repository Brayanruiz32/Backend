package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Compra;
import com.backend.entities.Fase;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    List<Compra> findAllByFase(Fase gestionando);

    @Query(value = "SELECT p.nombre AS nombreProveedor, SUM(c.total) AS total " +
    "FROM compras c " +
    "JOIN proveedores p ON c.proveedor_id = p.id " +
    "GROUP BY p.nombre", nativeQuery = true)
    List<Object[]> findTotalGroupedByProveedorNative();


}
