package com.sistema.ventas.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "linea_venta")
public class LineaVenta {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroLinea;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    private int cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;


    private LineaVenta() {
    }

    public LineaVenta(Venta venta, int cantidad, Double precioUnitario) {
        this.venta = venta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Long getNroLinea() {
        return nroLinea;
    }


    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
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
