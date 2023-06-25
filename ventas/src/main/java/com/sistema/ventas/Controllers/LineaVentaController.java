package com.sistema.ventas.Controllers;

import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Entities.UserInfo;
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

@RestController
@Slf4j
@RequestMapping("api/lineaVentas")

public class LineaVentaController {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    private LineaVentaService lineaVentaService;

    public LineaVentaController(LineaVentaService lineaVentaService){
        this.lineaVentaService=lineaVentaService;
    }


    @GetMapping("/getLineaVentas")
    public ResponseEntity<ApiResponse> getLineaVentas(){
        List<LineaVenta> productos= lineaVentaService.getLineaVentas();
        ApiResponse<List<LineaVenta>> ventaApiResponse= new ApiResponse<>(SUCCESS, productos);
        log.info("LineaVentaController::getLineaVentas response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);
    }


    @PostMapping("/createLineaVenta/{idProducto}")
    public ResponseEntity<ApiResponse> crearLineaVenta(@PathVariable Long idProducto, @Valid Integer cantidad) throws ServiceException{
        try {
        log.info("LineaVentaController::crearLineaVenta peticion {}", ValueMapper.jsonAsString(lineaVenta));
        LineaVenta lineaVenta1= lineaVentaService.crearLineaVenta(idProducto,cantidad);

        ApiResponse<LineaVenta> lineaVentaApiResponse= new ApiResponse<>(SUCCESS,lineaVenta1);
        log.info("productoController::createNewProducto respuesta {}", ValueMapper.jsonAsString(lineaVentaApiResponse));
        return new ResponseEntity<>(lineaVentaApiResponse, HttpStatus.CREATED);

        } catch (ServiceException ex) {
            ApiResponse<String> errorResponse = new ApiResponse<>(ERROR, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
