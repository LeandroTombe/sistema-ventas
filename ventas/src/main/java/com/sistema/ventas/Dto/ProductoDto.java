package com.sistema.ventas.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class ProductoDto {


    @Length(max = 20, message = "El nombre del producto no puede exceder de 20 caracteres")
    private String name;

    @Min(value = 0, message = "La cantidad de stock no puede ser menor a 0")
    private int stock;


    @Min(value =0, message = "La cantidad unitaria no puede ser menor a 0")
    @Max(value = 999999999, message = "el valor no puede ser mayor a 999999999")
    private Double precioUnitario;

    @NotBlank(message = "La fecha de actualizacion no puede ser nula")
    private LocalDateTime fechaActualizacion;

    public ProductoDto(String name, int stock, Double precioUnitario, LocalDateTime fechaActualizacion) {
        this.name = name;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}
