package com.sistema.ventas.Dto;

import jakarta.validation.constraints.NotNull;

public class NameEmailUserDto {


    @NotNull(message = "el nombre de usuario no puede ser nulo")
    private String name;

    @NotNull(message = "El email del usuario no debe ser nulo")
    private String email;


    public NameEmailUserDto(){};
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
