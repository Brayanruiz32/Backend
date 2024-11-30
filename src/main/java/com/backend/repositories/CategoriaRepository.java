package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    @Query(value = "SELECT p.nombre AS nombreProducto, l.numero_lote AS numeroLote, l.fecha_vencimiento AS fechaVencimiento " +
    "FROM categorias c " +
    "JOIN productos p ON p.categoria_id = c.id " +
    "JOIN lotes l ON l.categoria_id = c.id " +
    "WHERE l.fecha_vencimiento >= CURDATE() " +
    "ORDER BY l.fecha_vencimiento ASC", nativeQuery = true)
    List<Object[]> obtenerProductosYLotes();
}
