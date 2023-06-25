package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

    Optional<Producto> findByName(String name);
}
