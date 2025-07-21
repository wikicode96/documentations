package com.github.wikicode96.reactive.utils;

import com.github.wikicode96.reactive.entity.BasicEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EjecutarTareas {

    public void simularLectura() {
        try {
            System.out.println("\uD83D\uDCDA Simulando lectuea de archivo...");
            Thread.sleep(2000); // Simula tarea bloqueante
            System.out.println("✅ Lectura completada.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String simularEscritura(List<BasicEntity> elementos) {
        try {
            System.out.println("📝 Simulando escritura en archivo...");

            for (var e: elementos) System.out.println("Escribiendo elemento: " + e.toString());
            Thread.sleep(3000); // Simula tarea bloqueante

            System.out.println("✅ Escritura completada.");

            return "/path-archivo/mi-archivo.txt";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
