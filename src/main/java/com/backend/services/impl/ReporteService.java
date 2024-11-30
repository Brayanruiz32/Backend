package com.backend.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Lote;
import com.backend.entities.ProductoLoteDTO;
import com.backend.entities.ProductoStockDTO;
import com.backend.entities.ProductoVentaDTO;
import com.backend.entities.ProveedorTotalDTO;
import com.backend.entities.ReporteVentasDTO;
import com.backend.entities.UsuarioSaldoDTO;
import com.backend.repositories.CajaRepository;
import com.backend.repositories.CategoriaRepository;
import com.backend.repositories.CompraRepository;
import com.backend.repositories.DetalleCompraRepository;
import com.backend.repositories.DetalleVentaRepository;
import com.backend.repositories.LoteRepository;
import com.backend.repositories.ProductoRepository;
import com.backend.repositories.VentaRepository;

@Service

public class ReporteService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    //reporte de productos más vendidos
    public List<ProductoVentaDTO> ventaPorProducto(){
        List<Object[]> resultados = detalleVentaRepository.findVentasAgrupadasPorProducto();
        return resultados.stream()
            .map(r -> new ProductoVentaDTO((String) r[0], ((Number) r[1]).doubleValue()))
            .collect(Collectors.toList());
    }

    public List<ReporteVentasDTO> ventaPorCategoria(){
        List<Object[]> resultados = ventaRepository.obtenerReporteVentas();
        return resultados.stream()
            .map(fila -> new ReporteVentasDTO(
                (String) fila[0], 
                
                ((Number) fila[2]).doubleValue(), 
                ((Number) fila[1]).longValue()))
            .collect(Collectors.toList());
    }

    public List<ProveedorTotalDTO> listaPrecioDeCompraYProv() {
           List<Object[]> results = compraRepository.findTotalGroupedByProveedorNative();
                return results.stream()
                .map(row -> new ProveedorTotalDTO((String) row[0], ((Number) row[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    public List<UsuarioSaldoDTO> listarDetalleCajaDiaria(LocalDate fechaCierre) {
        LocalDateTime fechaCierreConHora = fechaCierre.atStartOfDay();
        List<Object[]> results = cajaRepository.findSaldoFinalCerradoGroupedByUsuario(fechaCierreConHora);
        return results.stream()
                      .map(row -> new UsuarioSaldoDTO((String) row[0], ((Number) row[1]).doubleValue()))
                      .collect(Collectors.toList());
    }

	public List<ProductoStockDTO> listarProductosBajosEnStock() {
        List<Lote> lotes = loteRepository.findAll();

            return lotes.stream()
            .flatMap(lote -> lote.getDetallesCompras().stream()) // Accede a los detalles de compras
            .filter(detalle -> detalle.getProducto().getStock() < 10) // Filtra por stock < 10
            .map(detalle -> new ProductoStockDTO(
                detalle.getProducto().getNombre(),
                detalle.getLote().getNumeroLote(),
                detalle.getLote().getAlmacen().getNombre(),
                detalle.getProducto().getStock()
            ))
            .collect(Collectors.toList());
	}
    
    public List<ProductoLoteDTO> listarProductosAVencer(){
        List<Object[]> resultados = categoriaRepository.obtenerProductosYLotes();

        return resultados.stream()
            .map(obj -> new ProductoLoteDTO(
                (String) obj[0],  // nombreProducto
                (String) obj[1],  // numeroLote
                obj[2].toString()   // fechaVencimiento
            ))
            .collect(Collectors.toList());
    }
    

    
}
