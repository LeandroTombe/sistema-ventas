package com.sistema.ventas.Dto;

import jakarta.validation.constraints.NotNull;

public class UserInfoDto {


    @NotNull(message = "el nombre de usuario no puede ser nulo")
    private String name;

    @NotNull(message = "El password no debe ser nulo")
    private String token;

    private String rol;


    public UserInfoDto(String name, String token, String rol){
        this.name=name;
        this.token=token;
        this.rol=rol;
    }


    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
