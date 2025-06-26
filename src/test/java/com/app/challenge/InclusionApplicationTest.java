package com.app.challenge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class InclusionApplicationTest {

    @Test
    @DisplayName("Debe cargar el contexto de la aplicación correctamente")
    void contextLoads() {
        // Este test verifica que el contexto de Spring Boot se cargue correctamente
        // No necesita assertions adicionales, el simple hecho de que el contexto
        // se cargue sin errores es suficiente para validar la configuración
    }

    @Test
    @DisplayName("Debe ejecutar el método main sin errores")
    void mainMethodShouldRun() {
        // Arrange & Act & Assert
        // Verificamos que el método main se pueda invocar sin lanzar excepciones
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            InclusionApplication.main(new String[]{});
        });
    }
}
