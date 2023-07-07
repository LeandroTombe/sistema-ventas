package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.Compra;
import com.sistema.ventas.Entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    Optional<Compra> findByfechaCreacion(LocalDate fecha);


}
