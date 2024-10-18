package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
