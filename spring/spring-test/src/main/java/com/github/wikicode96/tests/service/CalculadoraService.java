package com.github.wikicode96.tests.service;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public int suma(int a, int b) {
        return a + b;
    }

    public int multiplica(int a, int b) {
        return a * b;
    }
}
