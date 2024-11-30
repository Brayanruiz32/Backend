package com.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ReporteVentasDTO {
    private String categoria;
    // private String producto;
    private Long totalVendido;
    private Double totalIngresos;

    public ReporteVentasDTO(String categoria, Double totalIngresos, Long totalVendido) {
        this.categoria = categoria;
        // this.producto = producto;
        this.totalIngresos = totalIngresos;
        this.totalVendido = totalVendido;
    }

}
