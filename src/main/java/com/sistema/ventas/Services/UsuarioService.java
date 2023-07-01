package com.sistema.ventas.Services;


import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Repositories.UsuarioRepository;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }


    public UserInfo visualizarPerfil(String username){
        log.info("ClienteService:visualizarPerfil ejecucion iniciada");
        Optional<UserInfo> userInfo= usuarioRepository.findByName(username);

        if (userInfo.isPresent()){
            log.info("ClienteService:visualizarPerfil ejecucion finalizada");

            return userInfo.get();
        } else {
            log.error("ClienteService:visualizarPerfil Se ha producido un error");
            throw new ServiceException("El usuario no existe");
        }


    }

}
