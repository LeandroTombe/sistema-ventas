package com.sistema.ventas.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Dto.VentaDto;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Services.VentaService;
import com.sistema.ventas.Utils.ValueMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("api/ventas")
@Slf4j
public class VentaController {

    public static final String SUCCESS = "Success";

    @Autowired
    VentaService ventaService;

    @PostMapping("/createVenta")
    public ResponseEntity<ApiResponse> createNewVenta(@RequestBody @Valid VentaDto ventaDto) {
        ventaDto.setFechaCreacion(LocalDateTime.now());

        log.info("VentaController::createNewVenta request body {}", ValueMapper.jsonAsString(ventaDto));
        Venta venta = ventaService.createNewVenta(ventaDto);

        ApiResponse<Venta> ventaApiResponse = new ApiResponse<>(SUCCESS, venta);
        //String ventaApiResponseJson = objectMapper.writeValueAsString(ventaApiResponse);
        log.info("VentaController::createNewVenta response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.CREATED);

    }

}
