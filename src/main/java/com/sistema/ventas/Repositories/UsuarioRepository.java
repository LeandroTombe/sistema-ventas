package com.sistema.ventas.Repositories;

import com.sistema.ventas.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByName(String username);
}
