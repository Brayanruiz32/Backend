package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProveedorTotalDTO {

    private String nombreProveedor;
    private Double total;

}
