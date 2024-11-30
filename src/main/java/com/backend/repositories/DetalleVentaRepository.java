package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>{


    @Query(value = "SELECT p.nombre AS nombreProducto, SUM(d.subtotal) AS totalVentas " +
    "FROM detalle_ventas d " +
    "JOIN productos p ON d.producto_id = p.id " +
    "GROUP BY p.nombre", 
    nativeQuery = true)
    List<Object[]> findVentasAgrupadasPorProducto();
}
