package com.sistema.ventas.Controllers;


import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> visualizarPerfil(Authentication authentication) {
        try {
            if (authentication != null) {
                String perfilUser = authentication.getName();
                UserInfo userInfo = usuarioService.visualizarPerfil(perfilUser);

                ApiResponse<UserInfo> productoApiResponse = new ApiResponse<>(userInfo);
                log.info("ClienteController::visualizarPerfil peticion finalizada");

                return new ResponseEntity<>(productoApiResponse, HttpStatus.OK);
            } else {
                // Manejar el caso en el que no haya una autenticación válida
                String mensajeError = "No se ha realizado la autenticación, porque no esta logueado";
                ApiResponse<String> errorResponse = new ApiResponse<>(mensajeError);
                return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException ex) {
            // Manejar el error y retornar una respuesta de error apropiada
            String mensajeError = "Ocurrió un error al obtener el perfil del usuario";
            ApiResponse<String> errorResponse = new ApiResponse<>(mensajeError);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Deslogueo exitoso");
    }
}
