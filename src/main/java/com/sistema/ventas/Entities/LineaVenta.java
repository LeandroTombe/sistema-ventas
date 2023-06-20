package com.sistema.ventas.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Linea_venta")
public class LineaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea_venta")
    private Long idLineaVenta;




}
