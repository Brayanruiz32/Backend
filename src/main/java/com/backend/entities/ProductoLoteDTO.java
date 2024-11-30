package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoLoteDTO {
    private String nombreProducto;
    private String numeroLote;
    private String fechaVencimiento;
}
