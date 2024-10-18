package com.backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroLote;
    private LocalDate fechaIngreso;
    private LocalDate fechaVencimiento;

    // Relación con Producto (Un producto puede tener muchos lotes)
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Relación con Almacén (Un lote se almacena en un almacén específico)
    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

}
