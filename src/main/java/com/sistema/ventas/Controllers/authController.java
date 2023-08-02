package com.sistema.ventas.Controllers;

import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Dto.LoginDto;
import com.sistema.ventas.Dto.NameEmailUserDto;
import com.sistema.ventas.Dto.UserInfoDto;
import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Services.JwtService;
import com.sistema.ventas.Services.UserInfoDetailService;
import com.sistema.ventas.exception.GlobalExceptionHandler;
import com.sistema.ventas.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api/auth")
public class authController {


    @Autowired
    UserInfoDetailService userInfoDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    private JwtService jwtService;


    @PostMapping("/createUser")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid UserInfo userInfo) {
        try {
            UserInfo user = userInfoDetailService.addUser(userInfo);
            System.out.println(user);
            ApiResponse<UserInfo> userInfoApiResponse = new ApiResponse<>(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoApiResponse);
        } catch (ServiceException ex) {
            ApiResponse<String> errorResponse = new ApiResponse<>(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        List<NameEmailUserDto> usuarios= userInfoDetailService.getUsuarios();
        ApiResponse<List<NameEmailUserDto>> userInfoApiResponse = new ApiResponse<>(usuarios);
        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoApiResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(HttpServletRequest req, @RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            SecurityContext sc= SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpSession session = req.getSession(true);

            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            String logueadoCorrecto="logueado correctamente";
            ApiResponse<String> productoApiResponse = new ApiResponse<>(logueadoCorrecto);


            return ResponseEntity.ok().header("Content-Type", "application/json").body(loginDto.getUsername());
        } catch (Exception e) {
            throw new ServiceException("Error de autenticación: " + e.getMessage());
        }
    }



    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticatedAndGetToken(@RequestBody LoginDto loginDto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        if (authentication.isAuthenticated()){
            String userauth= jwtService.generateToken(loginDto.getUsername());
            String rol= authentication.getAuthorities().iterator().next().getAuthority();
            UserInfoDto userInfoDto= new UserInfoDto(loginDto.getUsername(),userauth,rol);
            ApiResponse<UserInfoDto> userAuthApiResponse = new ApiResponse<>(userInfoDto);
            return ResponseEntity.status(HttpStatus.OK).body(userAuthApiResponse);

        } else {
            throw new UsernameNotFoundException("invalid user request");
        }

    }
}
