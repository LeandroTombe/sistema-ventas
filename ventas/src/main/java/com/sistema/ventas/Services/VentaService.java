package com.sistema.ventas.Services;


import com.sistema.ventas.Dto.VentaDto;
import com.sistema.ventas.Entities.Venta;
import com.sistema.ventas.Repositories.VentaRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.VentaServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VentaService {

    //inyeccion de dependecias
    @Autowired
    VentaRepository ventaRepository;


    //crear un compra
    public Venta createNewVenta(VentaDto ventaDto) throws VentaServiceException {
        try {
            log.info("VentaService:createNewVenta ejecucion iniciada.");

            log.debug("VentaService:createNewVenta parametros {}", ValueMapper.jsonAsString(ventaDto));
            Venta venta = ValueMapper.convertToEntity(ventaDto);

            //Venta ventaNew = ventaRepository.save(venta);

            return venta;

        } catch (Exception ex) {
            log.error("Exception occurred while persisting product to database , Exception message {}", ex.getMessage());
            throw  new VentaServiceException("Ha ocurrido un problema al crear una venta en el servicio");

        }
    }

}
