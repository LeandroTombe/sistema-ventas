package com.sistema.ventas.Dto;

import jakarta.validation.constraints.NotNull;

public class UserInfoDto {


    @NotNull(message = "el nombre de usuario no puede ser nulo")
    private String name;

    @NotNull(message = "El password no debe ser nulo")
    private String token;


    public UserInfoDto(String name, String token){
        this.name=name;
        this.token=token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
