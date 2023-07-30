package com.sistema.ventas.Services;

import com.sistema.ventas.Config.UserInfoUserDetails;
import com.sistema.ventas.Dto.NameEmailUserDto;
import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Repositories.UserInfoRepository;
import com.sistema.ventas.Utils.ValueMapper;
import com.sistema.ventas.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserInfoDetailService implements UserDetailsService {


    @Autowired
    private UserInfoRepository userInfoRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfoOptional= userInfoRepository.findByName(username);

        return userInfoOptional.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found")
        );

    }


    public UserInfo addUser(UserInfo userInfo) throws ServiceException {
        log.info("UsuarioService:addUser ejecucion iniciada.");
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByName(userInfo.getName());

        if (optionalUserInfo.isPresent()) {
            log.error("El usuario ya existe");
            throw new ServiceException("El usuario ya existe");
        } else {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfo.setRoles("CLIENTE");
            UserInfo savedUserInfo = userInfoRepository.save(userInfo);
            log.debug("UserService:addUser devolviendo usuario creado de la base de datos {}", ValueMapper.jsonAsString(savedUserInfo));
            return savedUserInfo;
        }
    }

    public List<NameEmailUserDto> getUsuarios(){
        log.info("UsuarioService:getUsuarios ejecucion iniciada.");

        List<UserInfo> usuarios= userInfoRepository.findAll();

        List<NameEmailUserDto> usuariosDto= usuarios.stream().map(usuario-> {
            NameEmailUserDto nameEmailUserDto= new NameEmailUserDto();
            nameEmailUserDto.setName(usuario.getName());
            nameEmailUserDto.setEmail(usuario.getEmail());
            return nameEmailUserDto;
        }).collect(Collectors.toList());
        log.info("UsuarioService:getUsuarios ejecucion finalizada.");

        return  usuariosDto;

    }
}