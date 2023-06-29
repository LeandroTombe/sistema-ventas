package com.sistema.ventas.Services;


import com.sistema.ventas.Entities.LineaVenta;
import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.LineaVentaRepository;
import com.sistema.ventas.Repositories.ProductoRepository;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class LineaVentaService {

    //inyeccion de dependecias
    private LineaVentaRepository lineaVentaRepository;
    private ProductoRepository productoRepository;

    public LineaVentaService(LineaVentaRepository lineaVentaRepository,ProductoRepository productoRepository){
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


    @Transactional
    public LineaVenta crearLineaVenta(String nombreProducto, Integer cantidad) throws ServiceException {
        log.info("VentaService:crearLineaVenta ejecucion iniciada.");

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

            log.info("VentaService:crearLineaVenta ejecucion finalizada.\n");

            return lineaVentaRepository.save(lineaVenta);
        } else {
            throw new ServiceException("El producto con el nombre solicitado no existe");
        }
    }

    @Transactional
    public LineaVenta crearLineaVenta(Long idproducto, Integer cantidad) throws ServiceException {
        log.info("VentaService:crearLineaVenta ejecucion iniciada.");

        Optional<Producto> findProducto = productoRepository.findById(idproducto);

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

            log.info("Producto agregado: {}",producto);
            log.info("cantidad: {}",cantidad);

            //creamos una linea de venta
            LineaVenta lineaVenta= new LineaVenta();
            lineaVenta.setProducto(producto);
            lineaVenta.setCantidad(cantidad);
            lineaVenta.setPrecioUnitario(producto.getPrecioActual());

            /*
            //creacion del venta;
            Optional<Venta> venta=ventaService.findByFecha(LocalDate.now());

            if (venta.isEmpty()){
                log.info("Creando una venta nueva de la fecha de hoy");
                Venta newVenta = ventaService.createNewVenta(lineaVenta);
                lineaVenta.setVenta(newVenta);
            } else {
                log.info("Agregando la linea de venta a una venta existente de la misma fecha");
                Venta existVenta= venta.get();
                lineaVenta.setVenta(existVenta);
                existVenta.getLineaVentas().add(lineaVenta);
            }
            */

            log.info("VentaService:crearLineaVenta ejecucion finalizada.\n");

            return lineaVentaRepository.save(lineaVenta);
        } else {
            throw new ServiceException("El producto con el nombre solicitado no existe");
        }
    }

    public List<LineaVenta> crearLineasVentas(Map<String,Integer> mapeoProducto){
        List<LineaVenta> lineaVentas= new ArrayList<>();

        //el cliente me envia productos
        for (Map.Entry<String,Integer> entry: mapeoProducto.entrySet()){
            String nombreProducto= entry.getKey();
            Integer cantidadProducto= entry.getValue();

            LineaVenta lineaVenta= crearLineaVenta(nombreProducto,cantidadProducto);
            lineaVentas.add(lineaVenta);

            //creo una venta, por las lineas de ventas existentes
        }

        return lineaVentas;
    }
}
