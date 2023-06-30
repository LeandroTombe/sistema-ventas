package com.sistema.ventas.Controllers;


import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.LineaCompra;

import com.sistema.ventas.Services.LineaCompraService;
import com.sistema.ventas.Utils.ValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/Lineacompras")
public class LineaCompraController {




    private LineaCompraService lineaCompraService;

    public LineaCompraController(LineaCompraService lineaCompraService){
        this.lineaCompraService=lineaCompraService;
    }


    @GetMapping("/getLineaCompras")
    public ResponseEntity<ApiResponse> getLineaCompras(){
        List<LineaCompra> productos= lineaCompraService.getLineaCompra();
        ApiResponse<List<LineaCompra>> ventaApiResponse= new ApiResponse<>(productos);
        log.info("LineaCompraController::getLineaCompras response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);
    }



}
