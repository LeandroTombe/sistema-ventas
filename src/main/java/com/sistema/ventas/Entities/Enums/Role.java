package com.sistema.ventas.Entities.Enums;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    CLIENTE("ROLE_CLIENTE"),

    PROVEEDOR("ROLE_PROVEEDOR");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}