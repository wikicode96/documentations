package com.github.wikicode96;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Generamos una lista grande de clientes
        List<Cliente> clientes = generarClientes(50_000_000); // 1 millón de clientes

        String paisFiltrar = "España";

        // Medimos el tiempo del for-each tradicional
        long inicioForEach = System.nanoTime();
        List<Cliente> resultadoForEach = filtrarPorForEach(clientes, paisFiltrar);
        long finForEach = System.nanoTime();
        long tiempoForEach = finForEach - inicioForEach;

        // Medimos el tiempo del stream
        long inicioStream = System.nanoTime();
        List<Cliente> resultadoStream = filtrarPorStream(clientes, paisFiltrar);
        long finStream = System.nanoTime();
        long tiempoStream = finStream - inicioStream;

        // Medimos el tiempo del parallelStream
        long inicioParallelStream = System.nanoTime();
        List<Cliente> resultadoParallelStream = filtrarPorParallelStream(clientes, paisFiltrar);
        long finParallelStream = System.nanoTime();
        long tiempoParallelStream = finParallelStream - inicioParallelStream;

        // Mostramos los resultados
        System.out.println("Clientes encontrados (for-each): " + resultadoForEach.size());
        System.out.println("Tiempo for-each: " + tiempoForEach / 1_000_000.0 + " ms");

        System.out.println("Clientes encontrados (stream): " + resultadoStream.size());
        System.out.println("Tiempo stream: " + tiempoStream / 1_000_000.0 + " ms");

        System.out.println("Clientes encontrados (parallelStream): " + resultadoParallelStream.size());
        System.out.println("Tiempo parallelStream: " + tiempoParallelStream / 1_000_000.0 + " ms");
    }

    // Metodo para generar una lista de clientes aleatorios
    private static List<Cliente> generarClientes(int cantidad) {
        Random random = new Random();
        String[] paises = {"México", "Argentina", "España", "Colombia", "Chile"};

        return IntStream.range(0, cantidad)
                .mapToObj(i -> new Cliente("Cliente" + i, paises[random.nextInt(paises.length)]))
                .toList();
    }

    // Metodo con for-each
    private static List<Cliente> filtrarPorForEach(List<Cliente> clientes, String pais) {
        List<Cliente> filtrados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getPais().equals(pais)) {
                filtrados.add(cliente);
            }
        }
        return filtrados;
    }

    // Metodo con Streams
    private static List<Cliente> filtrarPorStream(List<Cliente> clientes, String pais) {
        return clientes.stream()
                .filter(cliente -> cliente.getPais().equals(pais))
                .collect(Collectors.toList());
    }

    // Metodo con ParallelStream
    private static List<Cliente> filtrarPorParallelStream(List<Cliente> clientes, String pais) {
        return clientes.parallelStream()
                .filter(cliente -> cliente.getPais().equals(pais))
                .collect(Collectors.toList());
    }
}