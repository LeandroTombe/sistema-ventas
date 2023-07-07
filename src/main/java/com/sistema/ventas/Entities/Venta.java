package com.sistema.ventas.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<LineaVenta> lineaVentas;


    private Venta() {

    }

    public Venta(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        this.lineaVentas=new ArrayList<>();
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<LineaVenta> getLineaVentas() {
        return lineaVentas;
    }

    public void setLineaVentas(List<LineaVenta> lineaVentas) {
        this.lineaVentas = lineaVentas;
    }


}
