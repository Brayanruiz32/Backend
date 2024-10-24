package com.backend.entities;

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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;
    
    private String codigoDeBarras;

    private Integer stock;
    
    @ManyToOne
    @JoinColumn(name="unidadmedida_id")
    private UnidadMedida unidadMedida;
    
    @ManyToOne
    @JoinColumn(name="marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria; 
    
}
