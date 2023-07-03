package com.sistema.ventas.Services;


import com.sistema.ventas.Dto.ReporteVentaDto;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.LineaVentaRepository;
import com.sistema.ventas.Repositories.ProductoRepository;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class VentaService {

    //inyeccion de dependecias

    private  VentaRepository ventaRepository;

    private LineaVentaService lineaVentaService;


    public VentaService(VentaRepository ventaRepository,LineaVentaService lineaVentaService){
        this.lineaVentaService=lineaVentaService;
        this.ventaRepository=ventaRepository;
    }

    //crear un venta
    public Venta createNewVenta(LineaVenta lineaVenta) throws ServiceException {
        try {

            log.info("VentaService:createNewVenta ejecucion iniciada.");
            Venta venta = new Venta(LocalDate.now());
            venta.getLineaVentas().add(lineaVenta);

            log.info("VentaService:createNewVenta ejecucion finalizada.\n");
            return ventaRepository.save(venta);
        } catch (Exception ex) {
            System.out.println(ex);
            throw  new ServiceException("Ha ocurrido un problema al crear una venta en el servicio");
        }
    }

    public List<Venta> getVentas() throws ServiceException {
        try{
            log.info("VentaService:getVentas ejecucion iniciada.");
            List<Venta> ventas= ventaRepository.findAll();
            log.debug("VentaService:getProducts devolviendo ventas de la base de datos {}", ValueMapper.jsonAsString(ventas));
            log.info("VentaService:getVentas ejecucion finalizada\n.");

            return ventas;
        } catch ( Exception ex){
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al mostrar las ventas en el servicio");
        }

    }


    public Optional<Venta> findByFecha(LocalDate fecha) throws ServiceException{
        log.info("VentaService:findByFecha ejecucion iniciada.");
        Optional<Venta> findVenta=ventaRepository.findByfechaCreacion(fecha);
        log.info("VentaService:findByFecha ejecucion finalizada\n.");
        return Optional.ofNullable(findVenta.orElse(null));
    }

    public List<Venta> getVentasByWeek() {
        log.info("VentaService:getVentaByWeek ejecucion iniciada.");

        List<Venta> ventasSemanaDiferencia = new ArrayList<>();
        List<Venta> ventas = ventaRepository.findAll();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaUnaSemanaAtras = fechaActual.minus(1, ChronoUnit.WEEKS);

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFechaCreacion();
            if (!fechaVenta.isBefore(fechaUnaSemanaAtras) && !fechaVenta.isAfter(fechaActual)) {
                ventasSemanaDiferencia.add(venta);
            }
        }

        log.info("VentaService:getVentaByWeek ejecucion finalizada.");
        return ventasSemanaDiferencia;
    }

    public List<Venta> getVentasByMonth(){
        log.info("VentaService:getVentasByMonth ejecucion iniciada.");
        List<Venta> ventasMesDiferencia = new ArrayList<>();
        List<Venta> ventas = ventaRepository.findAll();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaUnMesAtras = fechaActual.minus(1, ChronoUnit.MONTHS);

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFechaCreacion();
            if (!fechaVenta.isBefore(fechaUnMesAtras) && !fechaVenta.isAfter(fechaActual)) {
                ventasMesDiferencia.add(venta);
            }
        }

        log.info("VentaService:getVentasByMonth ejecucion finalizada.");
        return ventasMesDiferencia;
    }
    public List<Venta> getVentasByMonth(Integer numMes){
        log.info("VentaService:getVentasByEspecifyMonth ejecucion iniciada.");
        List<Venta> ventasMesDiferencia = new ArrayList<>();
        List<Venta> ventas = ventaRepository.findAll();

        LocalDate mesEspecifico= LocalDate.of(2023,numMes,1);

        LocalDate ultimoDiaMes= mesEspecifico.withDayOfMonth(mesEspecifico.lengthOfMonth());

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFechaCreacion();
            if (fechaVenta.isAfter(mesEspecifico.minusDays(1)) && fechaVenta.isBefore(ultimoDiaMes.plusDays(1))){
                ventasMesDiferencia.add(venta);
            }
        }

        log.info("VentaService:getVentasByEspecifyMonth ejecucion finalizada.");
        return ventasMesDiferencia;
    }

    public List<Venta> getVentasByYear(){
        log.info("VentaService:getVentasByYear ejecucion iniciada.");
        List<Venta> ventasAniosDiferencia = new ArrayList<>();
        List<Venta> ventas = ventaRepository.findAll();
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaUnAniosAtras = fechaActual.minus(1, ChronoUnit.YEARS);

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFechaCreacion();
            if (!fechaVenta.isBefore(fechaUnAniosAtras) && !fechaVenta.isAfter(fechaActual)) {
                ventasAniosDiferencia.add(venta);
            }
        }

        log.info("VentaService:getVentasByYear ejecucion finalizada.");
        return ventasAniosDiferencia;

    }

    public ReporteVentaDto getGananciasByWeek(){
        List<Venta> ventas=getVentasByWeek();
        ReporteVentaDto reporteVentaDto= new ReporteVentaDto();
        Double total=0.0;
        Integer cantidad=0;
        for (Venta venta:ventas) {

            reporteVentaDto.getVenta().add(venta);
            List<LineaVenta> lineaVentas=venta.getLineaVentas();
            for (LineaVenta lineaventa:lineaVentas) {

                total+=(lineaventa.getPrecioUnitario()* lineaventa.getCantidad());
                cantidad +=lineaventa.getCantidad();
            }
        }
        reporteVentaDto.setCantidad(cantidad);
        reporteVentaDto.setGananciaTotal(total);

        return reporteVentaDto;
    }

    public ReporteVentaDto getGananciasByMonth(){
        List<Venta> ventas=getVentasByMonth();
        ReporteVentaDto reporteVentaDto= new ReporteVentaDto();
        Double total=0.0;
        Integer cantidad=0;

        for (Venta venta:ventas) {
            reporteVentaDto.getVenta().add(venta);
            List<LineaVenta> lineaVentas=venta.getLineaVentas();

            for (LineaVenta lineaventa:lineaVentas) {
                total+=(lineaventa.getPrecioUnitario()* lineaventa.getCantidad());
                cantidad +=lineaventa.getCantidad();
            }
        }
        reporteVentaDto.setCantidad(cantidad);
        reporteVentaDto.setGananciaTotal(total);

        return reporteVentaDto;
    }

    public ReporteVentaDto getGananciasByMonth(Integer numMes){
        List<Venta> ventas=getVentasByMonth(numMes);
        ReporteVentaDto reporteVentaDto= new ReporteVentaDto();
        Double total=0.0;
        Integer cantidad=0;

        for (Venta venta:ventas) {
            reporteVentaDto.getVenta().add(venta);
            List<LineaVenta> lineaVentas=venta.getLineaVentas();

            for (LineaVenta lineaventa:lineaVentas) {
                total+=(lineaventa.getPrecioUnitario()* lineaventa.getCantidad());
                cantidad +=lineaventa.getCantidad();
            }
        }
        reporteVentaDto.setCantidad(cantidad);
        reporteVentaDto.setGananciaTotal(total);

        return reporteVentaDto;
    }

    public ReporteVentaDto getGananciasByYear(){
        List<Venta> ventas=getVentasByYear();
        ReporteVentaDto reporteVentaDto= new ReporteVentaDto();
        Double total=0.0;
        Integer cantidad=0;

        for (Venta venta:ventas) {
            reporteVentaDto.getVenta().add(venta);
            List<LineaVenta> lineaVentas=venta.getLineaVentas();

            for (LineaVenta lineaventa:lineaVentas) {
                total+=(lineaventa.getPrecioUnitario()* lineaventa.getCantidad());
                cantidad +=lineaventa.getCantidad();
            }
        }
        reporteVentaDto.setCantidad(cantidad);
        reporteVentaDto.setGananciaTotal(total);

        return reporteVentaDto;
    }


    public Venta createVenta(Map<String,Integer> mapeoProducto){

        log.info("VentaService:createVenta ejecucion iniciada.");
        Venta newVenta= new Venta(LocalDate.now());
        List<LineaVenta> lineaVentas=lineaVentaService.crearLineasVentas(mapeoProducto);

        // Asignar la venta a cada línea de venta
        lineaVentas.forEach(lineaVenta -> lineaVenta.setVenta(newVenta));

        newVenta.setLineaVentas(lineaVentas);
        log.info("VentaService:createVenta ejecucion finalizada.");

        return ventaRepository.save(newVenta);
    }

}
