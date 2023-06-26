package com.sistema.ventas.Controllers;



import com.sistema.ventas.Dto.ApiResponse;
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

@RestController
@RequestMapping("api/ventas")
@Slf4j
public class VentaController {

    public static final String SUCCESS = "Success";

    @Autowired
    VentaService ventaService;
    /*
    @PostMapping("/createVenta")
    public ResponseEntity<ApiResponse> createNewVenta(@RequestBody @Valid Venta venta) {

        log.info("VentaController::createNewVenta request body {}", ValueMapper.jsonAsString(venta));
        Venta newVenta = ventaService.createNewVenta(venta);

        ApiResponse<Venta> ventaApiResponse = new ApiResponse<>(SUCCESS, newVenta);
        log.info("VentaController::createNewVenta response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.CREATED);

    }
    */


    @GetMapping("/getVentas")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<ApiResponse> getVentas(){

        List<Venta> ventas= ventaService.getVentas();
        ApiResponse<List<Venta>> ventaApiResponse= new ApiResponse<>(SUCCESS, ventas);
        log.info("VentaController::getVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }



}
