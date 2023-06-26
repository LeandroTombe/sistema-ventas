package com.sistema.ventas.Services;


import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Repositories.LineaVentaRepository;
import com.sistema.ventas.Repositories.ProductoRepository;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LineaVentaService {

    //inyeccion de dependecias
    private VentaService ventaService;
    private LineaVentaRepository lineaVentaRepository;
    private ProductoRepository productoRepository;

    public LineaVentaService(VentaService ventaService,LineaVentaRepository lineaVentaRepository,ProductoRepository productoRepository){
        this.ventaService=ventaService;
        this.lineaVentaRepository=lineaVentaRepository;
        this.productoRepository=productoRepository;
    }

    public List<LineaVenta> getLineaVentas(){
        try {
            log.info("LineaVentaService:getLineaVentas ejecucion iniciada.");
            log.debug("Mostrando los productos");
            return lineaVentaRepository.findAll();
        }
        catch (Exception ex) {
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al mostrar los productos");
        }
    }


    public LineaVenta crearLineaVenta(String nombreProducto, Integer cantidad) throws ServiceException {
        log.info("VentaService:crearLineaVenta ejecucion iniciada.");
        log.debug("Busqueda del producto solicitado con nombre solicitado {}", ValueMapper.jsonAsString(nombreProducto));

        Optional<Producto> findProducto = productoRepository.findByName(nombreProducto);

        if (findProducto.isPresent()) {
            Producto producto = findProducto.get();

            if (cantidad > producto.getStock()) {
                log.error("VentaService:crearLineaVenta la cantidad solicitada es mayor al stock del producto");
                throw new ServiceException("La cantidad solicitada no puede ser mayor que el stock actual del producto");
            }
            //corresponde al objeto del producto
            producto.setStock(producto.getStock() - cantidad);
            producto.setFechaActualizacion(LocalDateTime.now());
            productoRepository.save(producto);


            //creamos una linea de venta
            LineaVenta lineaVenta= new LineaVenta();
            lineaVenta.setProducto(producto);
            lineaVenta.setCantidad(cantidad);
            lineaVenta.setPrecioUnitario(producto.getPrecioActual());



            log.info("VentaService:crearLineaVenta ejecucion finalizada.");

            return lineaVentaRepository.save(lineaVenta);
        } else {
            throw new ServiceException("El producto con el nombre solicitado no existe");
        }
    }
}
