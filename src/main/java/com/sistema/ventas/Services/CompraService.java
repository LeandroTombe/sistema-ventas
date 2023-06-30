package com.sistema.ventas.Services;

import com.sistema.ventas.Dto.ReporteCompraDto;
import com.sistema.ventas.Dto.ReporteVentaDto;
import com.sistema.ventas.Entities.Compra;
import com.sistema.ventas.Entities.LineaCompra;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.CompraRepository;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class CompraService {

    private LineaCompraService lineaCompraService;
    private CompraRepository compraRepository;

    public CompraService(LineaCompraService lineaCompraService, CompraRepository compraRepository){
        this.lineaCompraService=lineaCompraService;
        this.compraRepository=compraRepository;
    }

    public List<Compra> getCompras() throws ServiceException {
        try{
            log.info("CompraService:getCompras ejecucion iniciada.");
            List<Compra> compras= compraRepository.findAll();
            log.info("CompraService:getVentas ejecucion finalizada\n.");
            return compras;
        } catch ( Exception ex){
            log.error("error lanzado:", ex);
            throw new ServiceException("Ha ocurrido un problema al mostrar las ventas en el servicio");
        }

    }


    public Compra createCompra(Map<String,Integer> mapeoProducto){
        log.info("CompraService:createCompra ejecucion iniciada.");
        Compra newCompra= new Compra(LocalDate.now());

        List<LineaCompra> lineaCompras=lineaCompraService.crearLineaCompras(mapeoProducto);
        // Asignar la compra a cada línea de venta
        lineaCompras.forEach(lineaVenta -> lineaVenta.setCompra(newCompra));

        newCompra.setLineaCompras(lineaCompras);
        log.info("CompraService:createVenta ejecucion finalizada.");

        return compraRepository.save(newCompra);
    }


    public Optional<Compra> findByFecha(LocalDate fecha) throws ServiceException{
        log.info("CompraService:findByFecha ejecucion iniciada.");
        Optional<Compra> findVenta=compraRepository.findByfechaCreacion(fecha);
        log.info("CompraService:findByFecha ejecucion finalizada\n.");
        return Optional.ofNullable(findVenta.orElse(null));
    }

    public List<Compra> getComprasByWeek() {
        log.info("CompraService:getComprasByWeek ejecucion iniciada.");

        List<Compra> compraSemanaDiferencia = new ArrayList<>();
        List<Compra> compras = compraRepository.findAll();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaUnaSemanaAtras = fechaActual.minus(1, ChronoUnit.WEEKS);

        for (Compra compra : compras) {
            LocalDate fechaVenta = compra.getFechaCreacion();
            if (!fechaVenta.isBefore(fechaUnaSemanaAtras) && !fechaVenta.isAfter(fechaActual)) {
                compraSemanaDiferencia.add(compra);
            }
        }

        log.info("CompraService:getComprasByWeek ejecucion finalizada.");
        return compraSemanaDiferencia;
    }

    public ReporteCompraDto getGananciasByWeek(){
        List<Compra> compras=getComprasByWeek();
        ReporteCompraDto reporteCompraDto= new ReporteCompraDto();

        Double total=0.0;
        Integer cantidad=0;

        for (Compra compra:compras) {
            reporteCompraDto.getCompras().add(compra);
            List<LineaCompra> lineaCompras=compra.getLineaCompras();
            for (LineaCompra lineaCompra:lineaCompras) {
                total+=(lineaCompra.getPrecioUnitario()* lineaCompra.getCantidad());
                cantidad +=lineaCompra.getCantidad();
            }
        }
        reporteCompraDto.setCantidad(cantidad);
        reporteCompraDto.setPerdidaTotal(total);

        return reporteCompraDto;
    }
}
