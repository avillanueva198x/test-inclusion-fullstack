package com.app.challenge.application;

import com.app.challenge.application.handler.MathProblemHandler;
import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MathProblemHandlerTest {

    @Mock
    private MathProblemService mathProblemService;

    @InjectMocks
    private MathProblemHandler handler;

    private MathProblemRequest request;
    private MathProblemResponse expectedResponse;

    @BeforeEach
    void setup() {
        this.request = new MathProblemRequest(7L, 5L, 12345L);
        this.expectedResponse = new MathProblemResponse(12339L, 7L, 5L, 12345L);
    }

    @Test
    @DisplayName("Debe procesar la solicitud correctamente y delegar al servicio")
    void shouldProcessRequestSuccessfully() {
        // Arrange
        Mockito.when(this.mathProblemService.solveMathProblem(this.request))
            .thenReturn(this.expectedResponse);

        // Act
        MathProblemResponse response = this.handler.handle(this.request);

        // Assert
        Assertions.assertEquals(this.expectedResponse.result(), response.result());
        Assertions.assertEquals(this.expectedResponse.x(), response.x());
        Assertions.assertEquals(this.expectedResponse.y(), response.y());
        Assertions.assertEquals(this.expectedResponse.n(), response.n());

        Mockito.verify(this.mathProblemService).solveMathProblem(this.request);
    }

    @Test
    @DisplayName("Debe manejar diferentes casos de prueba correctamente")
    void shouldHandleDifferentTestCases() {
        // Arrange
        MathProblemRequest request1 = new MathProblemRequest(5L, 0L, 4L);
        MathProblemResponse response1 = new MathProblemResponse(0L, 5L, 0L, 4L);

        MathProblemRequest request2 = new MathProblemRequest(10L, 5L, 15L);
        MathProblemResponse response2 = new MathProblemResponse(15L, 10L, 5L, 15L);

        Mockito.when(this.mathProblemService.solveMathProblem(request1)).thenReturn(response1);
        Mockito.when(this.mathProblemService.solveMathProblem(request2)).thenReturn(response2);

        // Act
        MathProblemResponse result1 = this.handler.handle(request1);
        MathProblemResponse result2 = this.handler.handle(request2);

        // Assert
        Assertions.assertEquals(0L, result1.result());
        Assertions.assertEquals(15L, result2.result());

        Mockito.verify(this.mathProblemService).solveMathProblem(request1);
        Mockito.verify(this.mathProblemService).solveMathProblem(request2);
    }
}
