package com.sistema.ventas.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;


    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;


    @Column(name = "proveedor")
    private String proveedor;

    public Compra(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @OneToMany(mappedBy = "compra")
    private List<LineaCompra> lineaCompras;

    private Compra(){}

    public Compra(LocalDateTime fechaCreacion, List<LineaCompra> lineaCompras) {
        this.fechaCreacion = fechaCreacion;
        this.lineaCompras = new ArrayList<>();
    }

    public Compra(LocalDateTime fechaCreacion, String proveedor, List<LineaCompra> lineaCompras) {
        this.fechaCreacion = fechaCreacion;
        this.proveedor = proveedor;
        this.lineaCompras = lineaCompras;
    }

    public Long getIdCompra() {
        return idCompra;
    }


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public List<LineaCompra> getLineaCompras() {
        return lineaCompras;
    }

    public void setLineaCompras(List<LineaCompra> lineaCompras) {
        this.lineaCompras = lineaCompras;
    }
}
