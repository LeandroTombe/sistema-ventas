package com.sistema.ventas.exception;

import com.sistema.ventas.Dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String ERROR = "Error";

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponse> handleServiceException(ServiceException ex) {
        ApiResponse<String> errorResponse = new ApiResponse<>(ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}