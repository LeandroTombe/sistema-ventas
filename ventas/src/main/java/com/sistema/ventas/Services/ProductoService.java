package com.sistema.ventas.Services;


import com.sistema.ventas.Entities.Producto;
import com.sistema.ventas.Repositories.ProductoRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductoService {



    @Autowired
    ProductoRepository productoRepository;

    public Producto createProducto(Producto producto) throws ServiceException{
        log.info("ProductoService:createProducto ejecucion iniciada.");
        Optional<Producto> findProducto= productoRepository.findByName(producto.getName());
        if (findProducto.isPresent()){
            throw  new ServiceException("el nombre del producto ya existe");
        }
        log.debug("VentaService:createNewVenta parametros {}", ValueMapper.jsonAsString(producto));
        productoRepository.save(producto);
        return producto;
    }


    public List<Producto> getProductos(){
        try {
            log.info("ProductoService:getProductos ejecucion iniciada.");
            log.debug("Mostrando los productos");
            return productoRepository.findAll();
        }
        catch (Exception ex) {
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al mostrar los productos");
        }
    }

    public Producto updateProducto(Long id_producto, Producto productoActualizado){
        log.info("ProductoService:updateProducto ejecucion iniciada.");

        Optional<Producto> verificarProducto= productoRepository.findById(id_producto);
        if (!verificarProducto.isPresent()){
            log.error("error al obtener un producto");
            throw new ServiceException("producto que se busca para actualizar no existe");
        } else {
            // Verificar y actualizar solo los campos necesarios
            Producto producto = verificarProducto.get();

            // Verificar y actualizar solo los campos necesarios
            if (productoActualizado.getName() !=null) {
                verificarProducto.get().setName(productoActualizado.getName());
            }
            if (productoActualizado.getStock() != null) {
                producto.setStock(productoActualizado.getStock());
            }
            if (productoActualizado.getPrecioActual() != null) {
                producto.setPrecioActual(productoActualizado.getPrecioActual());
            }

            producto.setFechaActualizacion(LocalDateTime.now());

            Producto productoAct = productoRepository.save(producto); // Guardar los cambios

            log.info("ProductoService:updateProducto productos actualizados.");
            return productoAct;
        }

    }


    public void deleteProducto(Long id_producto){
        try {
            Optional<Producto> verificarProducto = productoRepository.findById(id_producto);

            if (verificarProducto.isPresent()) {

                productoRepository.delete(verificarProducto.get());
            } else {
                throw new ServiceException("No existe el producto solicitado");
            }
        } catch (Exception ex){
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al eliminar el producto");
        }
    }


    public Producto getProductoById(Long id_producto){
        Optional<Producto> verificarProducto = productoRepository.findById(id_producto);
        if (verificarProducto.isPresent()) {
            return verificarProducto.get();
        } else {
            throw new ServiceException("No existe el producto solicitado");
        }
    }


    public Producto getProductoByNme(String nombreProducto){
        Optional<Producto> verificarProducto = productoRepository.findByName(nombreProducto);
        if (verificarProducto.isPresent()) {
            return verificarProducto.get();
        } else {
            throw new ServiceException("No existe el producto solicitado con ese nombre");
        }
    }

    public void EliminarProducto(Long id_producto){
        Optional<Producto> verificarProducto = productoRepository.findById(id_producto);

        if (verificarProducto.isPresent()) {
            productoRepository.delete(verificarProducto.get());

        } else {
            throw new ServiceException("No existe el producto solicitado");
        }

    }
}
