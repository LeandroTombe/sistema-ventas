package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.LineaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LineaCompraRepository extends JpaRepository<LineaCompra,Long> {
}
