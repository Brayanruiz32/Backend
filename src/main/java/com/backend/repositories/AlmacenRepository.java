package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

    @Query("SELECT a FROM Almacen a WHERE a.id <> :almacenId")
    List<Almacen> findAllExcludingId(Long almacenId);

}
