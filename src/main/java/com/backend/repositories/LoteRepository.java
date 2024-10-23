package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entities.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{


    @Query("SELECT l FROM Lote l WHERE l.categoria.id = :categoriaProducto AND l.proceso = com.backend.entities.Proceso.ABIERTO")
    List<Lote> listaLotePorCategoriaYProceso(Long categoriaProducto);

    

}
