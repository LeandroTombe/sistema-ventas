package com.sistema.ventas.Dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class VentaDto {

    @NotBlank(message = "La fecha no puede ser nula")
    private LocalDateTime fechaCreacion;


    public VentaDto(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
