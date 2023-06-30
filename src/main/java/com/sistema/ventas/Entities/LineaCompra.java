package com.sistema.ventas.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "linea_compra")
public class LineaCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroLinea;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;


    private int cantidad;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    public LineaCompra(){}



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
