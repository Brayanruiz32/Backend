package com.backend.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;

    private LocalDate fechaVenta;

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private String numeroDocumento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")  
    private List<DetalleVenta> detalleVentas = new ArrayList<>();


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne()
    @JoinColumn(name="caja_id")
    private Caja caja;
}
