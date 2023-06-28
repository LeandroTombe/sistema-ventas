package com.sistema.ventas.Dto;


import com.sistema.ventas.Entities.Venta;

import java.util.ArrayList;
import java.util.List;


public class ReporteVentaDto {


    private List<Venta> ventas;


    private Integer cantidad;

    private Double gananciaTotal;


    public ReporteVentaDto(){
        this.ventas=new ArrayList<>();
    };

    public List<Venta> getVenta() {
        return ventas;
    }

    public void setVenta(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(Double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
}
