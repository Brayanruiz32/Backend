package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{


    @Query(value = """
        SELECT 
            c.nombre, 

            SUM(dv.cantidad), 
            SUM(dv.cantidad * dv.precioVenta)
        FROM Venta v
        JOIN v.detalleVentas dv
        JOIN dv.producto p
        JOIN p.categoria c
        GROUP BY c.id,c.nombre
        ORDER BY c.nombre, SUM(dv.cantidad * dv.precioVenta) DESC
        """)
    List<Object[]> obtenerReporteVentas();


}
