package com.sistema.ventas.Dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class LineaVentaDto {

    @Min(value = 0,message = "La cantidad no puede ser menor a 0")
    @Max(value = 99999, message = "La cantidad no puede ser mayor a 99999")
    private int cantidad;

    @Min(value =0, message = "La cantidad unitaria no puede ser menor a 0")
    @Max(value = 999999999, message = "el valor no puede ser mayor a 999999999")
    private Double precioUnitario;

    public LineaVentaDto(int cantidad, Double precioUnitario) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
