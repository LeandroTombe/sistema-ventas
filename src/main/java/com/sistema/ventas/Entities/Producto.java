package com.sistema.ventas.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;



    @Length(max = 20, message = "El nombre del producto no puede exceder de 20 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "La cantidad de stock no puede ser menor a 0")
    private Integer stock;


    @Min(value =0, message = "La cantidad unitaria no puede ser menor a 0")
    @Max(value = 999999999, message = "el valor no puede ser mayor a 999999999")
    @NotNull(message = "el precio actual puede estar vacio")
    @Column(name = "precio_actual")
    private Double precioActual;

    private String imageUri;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Producto() {
    }

    public Producto(@NotNull(message = "El nombre no puede ser nulo") String name, @NotNull(message = "El stock no puede ser nulo") Integer stock, @NotNull(message = "el precio actual puede estar vacio") Double precioActual, LocalDateTime fechaActualizacion) {
        this.name = name;
        this.stock = stock;
        this.precioActual = precioActual;
        this.fechaActualizacion = fechaActualizacion;
    }


    public Long getIdProducto() {
        return idProducto;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Double precioActual) {
        this.precioActual = precioActual;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


}
