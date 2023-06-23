package com.sistema.ventas.Dto;



import java.time.LocalDateTime;


public class VentaDto {

    private Long idVenta;

    private LocalDateTime fechaCreacion;



    public VentaDto(){}

    public VentaDto(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
}
