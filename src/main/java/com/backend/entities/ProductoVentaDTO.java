package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoVentaDTO {
    private String nombreProducto;
    private double totalVentas;
}
