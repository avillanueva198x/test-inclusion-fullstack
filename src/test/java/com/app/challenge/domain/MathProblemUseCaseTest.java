package com.app.challenge.domain;

import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import com.app.challenge.domain.usecase.MathProblemUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MathProblemUseCaseTest {

    @InjectMocks
    private MathProblemUseCase useCase;

    @Test
    @DisplayName("Debe calcular correctamente el máximo k para caso válido")
    void shouldCalculateMaxKSuccessfully() {
        // Arrange
        MathProblemRequest request = new MathProblemRequest(7L, 5L, 12345L);

        // Act
        MathProblemResponse response = this.useCase.solveMathProblem(request);

        // Assert
        Assertions.assertEquals(12339L, response.result());
        Assertions.assertEquals(7L, response.x());
        Assertions.assertEquals(5L, response.y());
        Assertions.assertEquals(12345L, response.n());
    }

    @Test
    @DisplayName("Debe calcular correctamente cuando y = n")
    void shouldCalculateWhenYEqualsN() {
        // Arrange
        MathProblemRequest request = new MathProblemRequest(10L, 5L, 5L);

        // Act
        MathProblemResponse response = this.useCase.solveMathProblem(request);

        // Assert
        Assertions.assertEquals(5L, response.result());
    }

    @Test
    @DisplayName("Debe calcular correctamente para números grandes")
    void shouldCalculateForLargeNumbers() {
        // Arrange - caso del ejemplo de Codeforces: x=2, y=0, n=999999999
        MathProblemRequest request = new MathProblemRequest(2L, 0L, 999999999L);

        // Act
        MathProblemResponse response = this.useCase.solveMathProblem(request);

        // Assert - k = 0 + floor((999999999-0)/2) * 2 = 0 + 499999999 * 2 = 999999998
        Assertions.assertEquals(999999998L, response.result());
    }

    @Test
    @DisplayName("Debe retornar -1 cuando y > n")
    void shouldReturnMinusOneWhenYGreaterThanN() {
        // Arrange
        MathProblemRequest request = new MathProblemRequest(10L, 15L, 5L);

        // Act
        MathProblemResponse response = this.useCase.solveMathProblem(request);

        // Assert
        Assertions.assertEquals(-1L, response.result());
    }

    @Test
    @DisplayName("Debe validar casos del ejemplo de Codeforces")
    void shouldValidateCodeforcesExamples() {
        // Test case 1: x=7, y=5, n=12345 -> expected=12339
        MathProblemRequest request1 = new MathProblemRequest(7L, 5L, 12345L);
        MathProblemResponse response1 = this.useCase.solveMathProblem(request1);
        Assertions.assertEquals(12339L, response1.result());

        // Test case 2: x=5, y=0, n=4 -> expected=0
        MathProblemRequest request2 = new MathProblemRequest(5L, 0L, 4L);
        MathProblemResponse response2 = this.useCase.solveMathProblem(request2);
        Assertions.assertEquals(0L, response2.result());

        // Test case 3: x=10, y=5, n=15 -> expected=15
        MathProblemRequest request3 = new MathProblemRequest(10L, 5L, 15L);
        MathProblemResponse response3 = this.useCase.solveMathProblem(request3);
        Assertions.assertEquals(15L, response3.result());

        // Test case 4: x=17, y=8, n=54321 -> expected=54306
        MathProblemRequest request4 = new MathProblemRequest(17L, 8L, 54321L);
        MathProblemResponse response4 = this.useCase.solveMathProblem(request4);
        Assertions.assertEquals(54306L, response4.result());
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando y >= x")
    void shouldThrowExceptionWhenYGreaterOrEqualToX() {
        // Arrange
        MathProblemRequest request1 = new MathProblemRequest(5L, 5L, 10L);
        MathProblemRequest request2 = new MathProblemRequest(5L, 7L, 10L);

        // Act & Assert
        var exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.useCase.solveMathProblem(request1);
        });

        var exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.useCase.solveMathProblem(request2);
        });

        Assertions.assertEquals("y debe ser menor que x", exception1.getMessage());
        Assertions.assertEquals("y debe ser menor que x", exception2.getMessage());
    }
} 