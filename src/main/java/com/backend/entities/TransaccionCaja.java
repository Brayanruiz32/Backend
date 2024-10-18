package com.backend.entities;

import java.time.LocalDateTime;

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
@Table(name = "transaccionesCaja")
public class TransaccionCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaTransaccion;
    private Double monto;
    private String tipoTransaccion; // "INGRESO" o "EGRESO"
    private String descripcion;

    // Relación con Caja
    @ManyToOne
    @JoinColumn(name = "caja_id")
    private Caja caja;

    // Relación con Usuario (quien realiza la transacción)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
