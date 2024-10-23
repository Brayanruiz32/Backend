package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Compra;
import com.backend.entities.Fase;

public interface CompraRepository extends JpaRepository<Compra, Long>{

    List<Compra> findAllByFase(Fase gestionando);

}
