package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoStockDTO {

    private String nombreProducto;
    private String numeroLote;
    private String nombreAlmacen;
    private int stock;
}
