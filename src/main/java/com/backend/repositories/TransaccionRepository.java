package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

}
