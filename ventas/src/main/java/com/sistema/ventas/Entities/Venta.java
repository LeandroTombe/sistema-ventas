package com.sistema.ventas.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "venta")
    private List<LineaVenta> lineaVentas;


}
