package com.sistema.ventas.Controllers;



import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Dto.ReporteVentaDto;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Services.VentaService;
import com.sistema.ventas.Utils.ValueMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/ventas")
@Slf4j
public class VentaController {


    @Autowired
    VentaService ventaService;



    @GetMapping("/getVentas")
    public ResponseEntity<ApiResponse> getVentas(){

        List<Venta> ventas= ventaService.getVentas();
        ApiResponse<List<Venta>> ventaApiResponse= new ApiResponse<>(ventas);
        log.info("VentaController::getVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }

    //obtener las fechas de una semana


    @GetMapping("/getVentasByWeek")
    public ResponseEntity<ApiResponse> getVentasByWeek(){

        List<Venta> ventas= ventaService.getVentasByWeek();
        ApiResponse<List<Venta>> ventaApiResponse= new ApiResponse<>(ventas);
        log.info("VentaController::getVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);
    }


    @GetMapping("/getGananciasByWeek")
    public ResponseEntity<ApiResponse> getGananciasByWeek(){

        ReporteVentaDto reporteVentaDto= ventaService.getGananciasByWeek();
        ApiResponse<ReporteVentaDto> reporteVentaApiResponse= new ApiResponse<>(reporteVentaDto);
        log.info("VentaController::getGananciasByWeek response {}", ValueMapper.jsonAsString(reporteVentaApiResponse));

        return new ResponseEntity<>(reporteVentaApiResponse, HttpStatus.OK);

    }

    @PostMapping("/createVenta")
    public ResponseEntity<ApiResponse> createVenta(@RequestBody @Valid Map<String,Integer> mapeoLineaVentas){
        Venta newVenta= ventaService.createVenta(mapeoLineaVentas);
        ApiResponse<Venta> ventaApiResponse= new ApiResponse<>(newVenta);
        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }




}
