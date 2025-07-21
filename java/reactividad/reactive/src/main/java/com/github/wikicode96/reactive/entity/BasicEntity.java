package com.github.wikicode96.reactive.entity;

import lombok.Data;

@Data
public class BasicEntity {
    // Simulación de una clase entidad (como si fuera de base de datos)

    private final String id;
    private final String nombre;

    public BasicEntity(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Entidad{id='" + id + "', nombre='" + nombre + "'}";
    }
}
