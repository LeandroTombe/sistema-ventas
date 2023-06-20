package com.sistema.ventas.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;


    private String name;

    private int stock;

    @Column(name = "precio_actual")
    private double precioActual;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;


    @OneToOne(mappedBy ="producto",cascade = CascadeType.ALL)
    private LineaVenta lineaVenta;

    @OneToOne(mappedBy ="producto", cascade = CascadeType.ALL)
    private LineaCompra lineaCompra;

    public Producto(String name, int stock, double precioActual, LocalDateTime fechaActualizacion, LineaVenta lineaVenta, LineaCompra lineaCompra) {
        this.name = name;
        this.stock = stock;
        this.precioActual = precioActual;
        this.fechaActualizacion = fechaActualizacion;
        this.lineaVenta = lineaVenta;
        this.lineaCompra = lineaCompra;
    }

    public Producto(String name, int stock, double precioActual, LocalDateTime fechaActualizacion) {
        this.name = name;
        this.stock = stock;
        this.precioActual = precioActual;
        this.fechaActualizacion = fechaActualizacion;
    }

    private Producto() {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LineaVenta getLineaVenta() {
        return lineaVenta;
    }

    public void setLineaVenta(LineaVenta lineaVenta) {
        this.lineaVenta = lineaVenta;
    }

    public LineaCompra getLineaCompra() {
        return lineaCompra;
    }

    public void setLineaCompra(LineaCompra lineaCompra) {
        this.lineaCompra = lineaCompra;
    }
}
