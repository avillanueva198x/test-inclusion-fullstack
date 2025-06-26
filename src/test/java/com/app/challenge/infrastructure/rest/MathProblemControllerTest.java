package com.app.challenge.infrastructure.rest;

import com.app.challenge.application.handler.MathProblemHandler;
import com.app.challenge.config.SecurityTestConfig;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(MathProblemController.class)
@Import({SecurityTestConfig.class, com.app.challenge.infrastructure.rest.advice.GlobalExceptionHandler.class})
class MathProblemControllerTest {

    private static final String ENDPOINT = "/api/v1/math/solve";
    private static final String JSON_MENSAJE = "$.mensaje";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MathProblemHandler mathProblemHandler;

    @Test
    @DisplayName("Debería resolver el problema matemático exitosamente")
    void shouldSolveMathProblemSuccessfully() {
        // Arrange
        var request = new MathProblemRequest(7L, 5L, 12345L);
        var expectedResponse = new MathProblemResponse(12339L, 7L, 5L, 12345L);

        Mockito.when(this.mathProblemHandler.handle(Mockito.any(MathProblemRequest.class)))
               .thenReturn(expectedResponse);

        Assertions.assertDoesNotThrow(() -> {
            // Act + Assert
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(12339L))
                .andExpect(jsonPath("$.x").value(7L))
                .andExpect(jsonPath("$.y").value(5L))
                .andExpect(jsonPath("$.n").value(12345L));
        });
    }

    @Test
    @DisplayName("Debe validar casos del ejemplo de Codeforces")
    void shouldValidateCodeforcesExamples() {
        Assertions.assertDoesNotThrow(() -> {
            // Test case 1: x=7, y=5, n=12345 -> expected=12339
            var request1 = new MathProblemRequest(7L, 5L, 12345L);
            var response1 = new MathProblemResponse(12339L, 7L, 5L, 12345L);
            Mockito.when(this.mathProblemHandler.handle(request1)).thenReturn(response1);

            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(12339L));

            // Test case 2: x=5, y=0, n=4 -> expected=0
            var request2 = new MathProblemRequest(5L, 0L, 4L);
            var response2 = new MathProblemResponse(0L, 5L, 0L, 4L);
            Mockito.when(this.mathProblemHandler.handle(request2)).thenReturn(response2);

            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0L));
        });
    }

    @Test
    @DisplayName("Debe retornar error cuando x es menor que 2")
    void shouldReturnBadRequestWhenXLessThanTwo() {
        var request = new MathProblemRequest(1L, 0L, 10L);

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(JSON_MENSAJE).exists());
        });
    }

    @Test
    @DisplayName("Debe retornar error cuando y es negativo")
    void shouldReturnBadRequestWhenYIsNegative() {
        var request = new MathProblemRequest(7L, -1L, 12345L);

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(JSON_MENSAJE).exists());
        });
    }

    @Test
    @DisplayName("Debe retornar error cuando n es menor que 1")
    void shouldReturnBadRequestWhenNLessThanOne() {
        var request = new MathProblemRequest(7L, 5L, 0L);

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(JSON_MENSAJE).exists());
        });
    }

    @Test
    @DisplayName("Debe retornar error cuando falta algún parámetro")
    void shouldReturnBadRequestWhenParameterIsMissing() {
        var invalidRequest = "{\"x\": 7, \"y\": 5}"; // missing 'n'

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(JSON_MENSAJE).exists());
        });
    }

    @Test
    @DisplayName("Debe retornar error cuando y >= x")
    void shouldReturnBadRequestWhenYGreaterOrEqualToX() {
        var request = new MathProblemRequest(5L, 5L, 10L);

        Mockito.when(this.mathProblemHandler.handle(Mockito.any(MathProblemRequest.class)))
               .thenThrow(new IllegalArgumentException("y debe ser menor que x"));

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(JSON_MENSAJE).value("y debe ser menor que x"));
        });
    }

    @Test
    @DisplayName("Debe retornar error 500 si ocurre una excepción no controlada")
    void shouldReturnInternalServerErrorOnUnhandledException() {
        var request = new MathProblemRequest(7L, 5L, 12345L);

        Mockito.when(this.mathProblemHandler.handle(Mockito.any(MathProblemRequest.class)))
               .thenThrow(new RuntimeException("Fallo inesperado"));

        Assertions.assertDoesNotThrow(() -> {
            this.mockMvc.perform(post(ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath(JSON_MENSAJE).value("Error interno del servidor"));
        });
    }
}
