package com.sistema.ventas.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sistema.ventas.Entities.Compra;
import com.sistema.ventas.Entities.Venta;

public class ValueMapper {

    /*
    public static Compra convertToEntity(CompraDto compraDto){
        Compra compra= new Compra();
        compra.setFechaCreacion(compraDto.getFechaCreacion());
        return compra;
    }

    public static Venta convertToEntity(VentaDto ventaDto){
        Venta venta= new Venta();
        venta.setFechaCreacion(ventaDto.getFechaCreacion());
        return venta;
    }

    public static VentaDto convertToDto(Venta venta){
        VentaDto ventaDto= new VentaDto();
        ventaDto.setIdVenta(venta.getIdVenta());
        ventaDto.setFechaCreacion(venta.getFechaCreacion());

        return ventaDto;
    }
    */

    //crear una instancia de Object mapper para devolver un Json a cadena de texto o String
    public static String jsonAsString(Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
