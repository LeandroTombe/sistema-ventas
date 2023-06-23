package com.sistema.ventas.Dto;

import jakarta.validation.constraints.NotNull;

public class UserInfoDto {



    @NotNull(message = "el nombre de usuario no puede ser nulo")
    private String name;

    @NotNull(message = "El email del usuario no debe ser nulo")
    private String email;

    @NotNull(message = "El password no debe ser nulo")
    private String password;

    private String roles;



}
