package com.sistema.ventas.Controllers;


import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Services.ProductoService;
import com.sistema.ventas.Utils.ValueMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/productos")
public class ProductoController {

    public static final String SUCCESS = "Success";


    @Autowired
    ProductoService productoService;

    @GetMapping("/getProductos")
    @PreAuthorize("hasAuthority('CLIENTE')")

    public ResponseEntity<ApiResponse> getProductos(){
        List<Producto> productos= productoService.getProductos();
        ApiResponse<List<Producto>> ventaApiResponse= new ApiResponse<>(SUCCESS, productos);
        log.info("ProductController::getProducts response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }


    @PostMapping("/createProducto")
    public ResponseEntity<ApiResponse> createNewProducto(@RequestBody @Valid Producto producto) {

        log.info("ProductoController::createNewProducto peticion {}", ValueMapper.jsonAsString(producto));
        producto.setFechaActualizacion(LocalDateTime.now());
        Producto nuevoProducto = productoService.createProducto(producto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(SUCCESS, producto);
        log.info("productoController::createNewProducto response {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.CREATED);

    }

    @PutMapping("/updateProducto/{id_producto}")
    public ResponseEntity<ApiResponse> updateProducto( @Valid @PathVariable Long id_producto, @RequestBody Producto producto) {

        log.info("productoController::updateProducto peticion iniciada con el id {}", ValueMapper.jsonAsString(id_producto));
        Producto nuevoProducto = productoService.updateProducto(id_producto,producto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(SUCCESS, nuevoProducto);
        log.info("productoController::updateProducto response {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }

    @DeleteMapping("/deleteProducto/{id_producto}")
    public ResponseEntity<ApiResponse> updateProducto( @PathVariable Long id_producto) {

        log.info("productoController::deleteProducto peticion iniciada con el id {}", ValueMapper.jsonAsString(id_producto));
        productoService.EliminarProducto(id_producto);

        ApiResponse<String> productoApiResponse = new ApiResponse<>(SUCCESS,"Producto eliminado");
        log.info("productoController::deleteProducto respuesta {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }
}
