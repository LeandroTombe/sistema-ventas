package com.sistema.ventas.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
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
    private LocalDate fechaCreacion;



    @JsonManagedReference
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<LineaCompra> lineaCompras;

    public Compra(){}

    public Compra(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Compra(LocalDate fechaCreacion, List<LineaCompra> lineaCompras) {
        this.fechaCreacion = fechaCreacion;
        this.lineaCompras = new ArrayList<>();
    }


    public Long getIdCompra() {
        return idCompra;
    }


    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }



    public List<LineaCompra> getLineaCompras() {
        return lineaCompras;
    }

    public void setLineaCompras(List<LineaCompra> lineaCompras) {
        this.lineaCompras = lineaCompras;
    }
}
