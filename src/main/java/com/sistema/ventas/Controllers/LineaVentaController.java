package com.sistema.ventas.Controllers;

import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Services.LineaVentaService;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/lineaVentas")

public class LineaVentaController {



    private LineaVentaService lineaVentaService;

    public LineaVentaController(LineaVentaService lineaVentaService){
        this.lineaVentaService=lineaVentaService;
    }


    @GetMapping("/getLineaVentas")
    public ResponseEntity<ApiResponse> getLineaVentas(){
        List<LineaVenta> productos= lineaVentaService.getLineaVentas();
        ApiResponse<List<LineaVenta>> ventaApiResponse= new ApiResponse<>(productos);
        log.info("LineaVentaController::getLineaVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);
    }


    @PostMapping("/createLineaVenta")
    public ResponseEntity<ApiResponse> crearLineaVenta(@RequestParam String nombreProducto, @RequestParam Integer cantidad) throws ServiceException{
        try {
        log.info("LineaVentaController::crearLineaVenta cantidad y nombre {} {}",ValueMapper.jsonAsString(nombreProducto),  ValueMapper.jsonAsString(cantidad));
        LineaVenta lineaVenta= lineaVentaService.crearLineaVenta(nombreProducto,cantidad);

        ApiResponse<LineaVenta> lineaVentaApiResponse= new ApiResponse<>(lineaVenta);
        log.info("productoController::createNewProducto respuesta {}", ValueMapper.jsonAsString(lineaVentaApiResponse));
        return new ResponseEntity<>(lineaVentaApiResponse, HttpStatus.CREATED);

        } catch (ServiceException ex) {
            ApiResponse<String> errorResponse = new ApiResponse<>(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }



}
