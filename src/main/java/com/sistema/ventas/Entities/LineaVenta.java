package com.sistema.ventas.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "linea_venta")
public class LineaVenta {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroLinea;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;


    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad de producto no puede ser menor a 0")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;


    public LineaVenta() {
    }

    public LineaVenta(Venta venta, Integer cantidad, Double precioUnitario, Producto producto) {
        this.venta = venta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.producto=producto;
    }

    public Long getNroLinea() {
        return nroLinea;
    }

    public void setNroLinea(Long nroLinea) {
        this.nroLinea = nroLinea;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
