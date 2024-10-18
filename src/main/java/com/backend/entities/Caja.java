package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "caja")
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private Double saldoInicial;
    private Double saldoFinal;
    private String estado; // "ABIERTA" o "CERRADA"

    // Relación con Usuario (quien realiza la apertura o cierre de caja)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación con TransaccionesCaja (registro de ingresos y egresos de dinero)
    @OneToMany(mappedBy = "caja", cascade = CascadeType.ALL)
    private List<TransaccionCaja> transaccionesCaja = new ArrayList<>();
    
}
