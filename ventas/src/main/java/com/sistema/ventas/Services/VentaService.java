package com.sistema.ventas.Services;


import com.sistema.ventas.Dto.VentaDto;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentaService {

    //inyeccion de dependecias
    @Autowired
    VentaRepository ventaRepository;


    //crear un venta
    public Venta createNewVenta(VentaDto ventaDto) throws ServiceException {
        try {
            log.info("VentaService:createNewVenta ejecucion iniciada.");
            log.debug("VentaService:createNewVenta parametros {}", ValueMapper.jsonAsString(ventaDto));
            Venta venta = ValueMapper.convertToEntity(ventaDto);
            Venta ventaNew = ventaRepository.save(venta);
            return venta;

        } catch (Exception ex) {
            log.error("error lanzado:", ex.getMessage());
            throw  new ServiceException("Ha ocurrido un problema al crear una venta en el servicio");

        }
    }

    public List<VentaDto> getVentas() throws ServiceException {

        List<VentaDto> ventasDto= null;

        try{
            log.info("VentaService:getVentas ejecucion iniciada.");
            List<Venta> ventas= ventaRepository.findAll();
            if (!ventas.isEmpty()){
                ventasDto=ventas.stream().map(ValueMapper::convertToDto).collect(Collectors.toList());
            } else {
                ventasDto= Collections.emptyList();
            }

            log.debug("VentaService:getProducts devolviendo ventas de la base de datos {}", ValueMapper.jsonAsString(ventasDto));

        } catch ( Exception ex){
            log.error("error lanzado:", ex.getMessage());
            throw new ServiceException("Ha ocurrido un problema al mostrar las ventas en el servicio");
        }

        log.info("ProductService:getProducts execution ended.");
        return ventasDto;
    }
}
