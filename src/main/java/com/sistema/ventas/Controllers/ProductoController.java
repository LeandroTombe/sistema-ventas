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


    @Autowired
    ProductoService productoService;

    @GetMapping("/getProductos")
    //@PreAuthorize("hasAuthority('CLIENTE')")

    public ResponseEntity<ApiResponse> getProductos(){
        List<Producto> productos= productoService.getProductos();
        ApiResponse<List<Producto>> ventaApiResponse= new ApiResponse<>(productos);
        log.info("ProductController::getProducts response {}", ValueMapper.jsonAsString(ventaApiResponse));

        return new ResponseEntity<>(ventaApiResponse, HttpStatus.OK);

    }


    @PostMapping("/createProducto")
    public ResponseEntity<ApiResponse> createNewProducto(@RequestBody @Valid Producto producto) {

        log.info("ProductoController::createNewProducto peticion {}", ValueMapper.jsonAsString(producto));
        producto.setFechaActualizacion(LocalDateTime.now());
        Producto nuevoProducto = productoService.createProducto(producto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(producto);
        log.info("productoController::createNewProducto response {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.CREATED);

    }

    @PutMapping("/updateProducto/{id_producto}")
    public ResponseEntity<ApiResponse> updateProducto( @Valid @PathVariable Long id_producto, @RequestBody Producto producto) {

        log.info("productoController::updateProducto peticion iniciada con el id {}", ValueMapper.jsonAsString(id_producto));
        Producto nuevoProducto = productoService.updateProducto(id_producto,producto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(nuevoProducto);
        log.info("productoController::updateProducto response {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }

    @DeleteMapping("/deleteProducto/{id_producto}")
    public ResponseEntity<ApiResponse> updateProducto( @PathVariable Long id_producto) {

        log.info("productoController::deleteProducto peticion iniciada con el id {}", ValueMapper.jsonAsString(id_producto));
        productoService.EliminarProducto(id_producto);

        ApiResponse<String> productoApiResponse = new ApiResponse<>("Producto eliminado");
        log.info("productoController::deleteProducto respuesta {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }


    @GetMapping("/buscarProductoId/{id_producto}")
    public ResponseEntity<ApiResponse> findProductById( @PathVariable Long id_producto) {

        log.info("productoController::findProductById peticion iniciada con el id {}", ValueMapper.jsonAsString(id_producto));
        Producto findProducto= productoService.getProductoById(id_producto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(findProducto);
        log.info("productoController::findProductById respuesta {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }

    @GetMapping("/buscarProductoNombre/{nombreProducto}")
    public ResponseEntity<ApiResponse> findProductByName( @PathVariable String nombreProducto) {

        log.info("productoController::findProductByName peticion iniciada con el nombre {}", ValueMapper.jsonAsString(nombreProducto));
        Producto findProducto=productoService.getProductoByNme(nombreProducto);

        ApiResponse<Producto> productoApiResponse = new ApiResponse<>(findProducto);
        log.info("productoController::findProductByName respuesta {}", ValueMapper.jsonAsString(productoApiResponse));

        return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);

    }
}
