package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT(:nombre, '%'))")
    List<Producto> findAllByName(String nombre);

}
