package com.sistema.ventas.Dto;

import com.sistema.ventas.Entities.Compra;

import java.util.ArrayList;
import java.util.List;

public class ReporteCompraDto {

    private List<Compra> compras;
    private Integer cantidad;

    private Double PerdidaTotal;


    public ReporteCompraDto() {
        this.compras=new ArrayList<>();
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPerdidaTotal() {
        return PerdidaTotal;
    }

    public void setPerdidaTotal(Double perdidaTotal) {
        PerdidaTotal = perdidaTotal;
    }
}
