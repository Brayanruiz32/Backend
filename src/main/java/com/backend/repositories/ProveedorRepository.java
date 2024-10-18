package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}
