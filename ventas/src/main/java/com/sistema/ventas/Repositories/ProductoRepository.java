package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
