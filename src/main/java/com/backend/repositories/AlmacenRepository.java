package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Almacen;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

}
