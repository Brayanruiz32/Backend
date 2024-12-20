package com.backend.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.entities.Caja;
import com.backend.entities.Estado;

public interface CajaRepository extends JpaRepository<Caja, Long> {

    Optional<Caja> findFirstByEstadoOrderByIdDesc(Estado estado);

    @Query("SELECT c FROM Caja c WHERE c.usuario.id = :usuarioId")
    List<Caja> encuentraCajasPorVendedor(Long usuarioId);

    @Query("SELECT c.usuario.nombre, SUM(c.saldoFinal) " +
    "FROM Caja c " +
    "WHERE c.estado = 'CERRADA' AND c.fechaCierre >= :fechaCierre " +
    "GROUP BY c.usuario.nombre")
List<Object[]> findSaldoFinalCerradoGroupedByUsuario(@Param("fechaCierre") LocalDateTime fechaCierre);


}
