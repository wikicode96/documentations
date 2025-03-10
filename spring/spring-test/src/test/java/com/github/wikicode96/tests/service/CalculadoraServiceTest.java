package com.github.wikicode96.tests.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculadoraServiceTest {

    @Mock
    private CalculadoraService mockCalculadora;

    @Spy
    private CalculadoraService spyCalculadora;

    @Test
    void testSumaMock() {
        when(mockCalculadora.suma(2, 3)).thenReturn(100); // Falseamos la respuesta

        int resultadoSuma = mockCalculadora.suma(2, 3);
        int resultadoMultiplicacion = mockCalculadora.multiplica(2, 3);

        Assertions.assertEquals(100, resultadoSuma); // OK: Devuelve el valor falseado
        Assertions.assertEquals(0, resultadoMultiplicacion); // ERROR: Devuelve 0 porque todo está mockeado
    }

    @Test
    void testSumaSpy() {
        doReturn(100).when(spyCalculadora).suma(2, 3); // Falseamos solo suma()

        int resultadoSuma = spyCalculadora.suma(2, 3);
        int resultadoMultiplicacion = spyCalculadora.multiplica(2, 3);

        Assertions.assertEquals(100, resultadoSuma); // OK: Devuelve el valor falseado
        Assertions.assertEquals(6, resultadoMultiplicacion); // OK: Ejecuta la lógica real
    }
}