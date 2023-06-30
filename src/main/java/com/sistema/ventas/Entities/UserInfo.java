package com.sistema.ventas.Entities;

import com.sistema.ventas.Entities.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserInfo {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @NotNull(message = "el nombre de usuario no puede ser nulo")
    private String name;

    @NotNull(message = "El email del usuario no debe ser nulo")
    @Email(message = "Debe ser un email valido")
    private String email;

    @NotNull(message = "El password no debe ser nulo")
    private String password;

    //@Enumerated(value = EnumType.STRING)
    private Role roles;


    public UserInfo() {
    }

    public UserInfo(String name, String email, String password,Role roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles=roles;
    }

    public Long getUserId() {
        return userId;
    }



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
