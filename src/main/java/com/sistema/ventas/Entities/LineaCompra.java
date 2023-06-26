package com.sistema.ventas.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "linea_compra")
public class LineaCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroLinea;


    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;


    private int cantidad;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @OneToOne
    private Producto producto;



    private LineaCompra(){}


    public LineaCompra(Compra compra, int cantidad, double precioUnitario) {
        this.compra = compra;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public LineaCompra(Compra compra, Producto producto, int cantidad, double precioUnitario) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getNroLinea() {
        return nroLinea;
    }


    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
