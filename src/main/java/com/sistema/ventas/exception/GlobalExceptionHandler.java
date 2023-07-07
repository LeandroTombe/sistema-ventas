package com.sistema.ventas.exception;

import com.sistema.ventas.Dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR = "Error";

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse> handleServiceException(ServiceException ex) {
        ApiResponse<String> errorResponse = new ApiResponse<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Crea una respuesta de error personalizada
        ApiResponse errorResponse = new ApiResponse("Error: Falta agregar atributos del body");

        // Devuelve la respuesta de error con el estado HTTP apropiado
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }





    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        // Manejar el error y retornar una respuesta apropiada
        String mensajeError = "Ocurrió un error de NullPointerException";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }
}