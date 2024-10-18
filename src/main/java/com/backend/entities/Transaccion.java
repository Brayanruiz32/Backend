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
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private LocalDate fechaTransaccion;

    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    
    @ManyToOne
    @JoinColumn(name = "almacen_origen_id")
    private Almacen almacenOrigen;

    
    @ManyToOne
    @JoinColumn(name = "almacen_destino_id")
    private Almacen almacenDestino;
}
