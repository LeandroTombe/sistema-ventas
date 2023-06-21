package com.sistema.ventas.exception;


import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    //controlar las validaciones de cada campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){

        ApiResponse<?> apiResponse= new ApiResponse<>();
        List<ErrorDto> errors=new ArrayList<>();
        //obtener los errores de cada validacion y guardarlas en una lista para visualizar
        exception.getBindingResult().getFieldErrors().forEach(
                error -> {
                    ErrorDto errorDto= new ErrorDto(error.getField(), error.getDefaultMessage());
                    errors.add(errorDto);
                });
        apiResponse.setStatus("FALLIDO");
        apiResponse.setErrors(errors);

        return  apiResponse;
    }
}
