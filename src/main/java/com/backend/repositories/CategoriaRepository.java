package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
