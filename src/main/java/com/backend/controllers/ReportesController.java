package com.backend.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entities.ProductoLoteDTO;
import com.backend.entities.ProductoStockDTO;
import com.backend.entities.ProductoVentaDTO;
import com.backend.entities.ProveedorTotalDTO;
import com.backend.entities.ReporteVentasDTO;
import com.backend.entities.UsuarioSaldoDTO;
import com.backend.services.impl.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReportesController {


    @Autowired
    private ReporteService reporteService;

    @GetMapping("/topVentas")
    public ResponseEntity<List<ProductoVentaDTO>> listarVentasPorProducto(){
        return new ResponseEntity<>(reporteService.ventaPorProducto(), HttpStatus.OK);
    }

    @GetMapping("/ventaPorCategoria")
    public ResponseEntity<List<ReporteVentasDTO>> listarVentasPorCategoria(){
        return new ResponseEntity<>(reporteService.ventaPorCategoria(), HttpStatus.OK);
    }

    @GetMapping("/comprasYProveedores")
    public ResponseEntity<List<ProveedorTotalDTO>> listarPreciosDeCompraYProveedores(){
        return new ResponseEntity<>(reporteService.listaPrecioDeCompraYProv(), HttpStatus.OK);
    } 

    @GetMapping("/cierreDeCaja")
    public ResponseEntity<List<UsuarioSaldoDTO>> listarDetalleCajaDiaria(@RequestParam LocalDate fechaCierre){
        return new ResponseEntity<>(reporteService.listarDetalleCajaDiaria(fechaCierre), HttpStatus.OK);
    }

    @GetMapping("/productosVencer")
    public ResponseEntity<List<ProductoLoteDTO>> listaProductosAVencer(){
        return new ResponseEntity<>(reporteService.listarProductosAVencer(), HttpStatus.OK);
    }
    
    @GetMapping("/productosBajosEnStock")
    public ResponseEntity<List<ProductoStockDTO>> listarProductosBajosEnStock(){
        return new ResponseEntity<>(reporteService.listarProductosBajosEnStock(), HttpStatus.OK);
    }

}
