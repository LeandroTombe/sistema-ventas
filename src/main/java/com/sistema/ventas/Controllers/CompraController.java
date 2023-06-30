package com.sistema.ventas.Controllers;


import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Dto.ReporteCompraDto;
import com.sistema.ventas.Dto.ReporteVentaDto;
import com.sistema.ventas.Entities.Compra;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Services.CompraService;
import com.sistema.ventas.Utils.ValueMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/compras")
public class CompraController {

    private CompraService compraService;

    public CompraController(CompraService compraService){
        this.compraService=compraService;
    }


    @GetMapping("/getCompras")
    public ResponseEntity<ApiResponse> getVentas(){

        List<Compra> compras= compraService.getCompras();
        ApiResponse<List<Compra>> ventaApiResponse= new ApiResponse<>(compras);
        log.info("VentaController::getVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }

    @PostMapping("/createCompra")
    public ResponseEntity<ApiResponse> createCompra(@RequestBody @Valid Map<String,Integer> mapeoLineaCompras){

        Compra newCompra= compraService.createCompra(mapeoLineaCompras);
        ApiResponse<Compra> compraApiResponse= new ApiResponse<>(newCompra);
        return new ResponseEntity<>(compraApiResponse, HttpStatus.OK);
    }

    @GetMapping("/getGananciasByWeek")
    public ResponseEntity<ApiResponse> getGananciasByWeek(){

        ReporteCompraDto reporteCompraDto= compraService.getGananciasByWeek();
        ApiResponse<ReporteCompraDto> reporteVentaApiResponse= new ApiResponse<>(reporteCompraDto);
        log.info("CompraController::getGananciasByWeek response {}", ValueMapper.jsonAsString(reporteVentaApiResponse));
        return new ResponseEntity<>(reporteVentaApiResponse, HttpStatus.OK);

    }
}
