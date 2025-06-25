package com.app.challenge.infrastructure.rest;

import com.app.challenge.application.handler.MathProblemHandler;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST para resolver el problema matemático de Codeforces 1374A.
 * 
 * Este controller expone un endpoint que permite encontrar el máximo entero k
 * tal que 0 ≤ k ≤ n y k mod x = y, donde x, y, n son parámetros de entrada.
 * 
 * Implementa arquitectura hexagonal delegando la lógica de negocio al handler
 * correspondiente, manteniendo las responsabilidades bien separadas.
 * 
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/v1/math")
@RequiredArgsConstructor
@Tag(name = "Problema Matemático", description = "Resolución del problema de Codeforces 1374A")
public class MathProblemController {

    /** Handler que contiene la lógica de orquestación para resolver el problema matemático */
    private final MathProblemHandler mathProblemHandler;

    /**
     * Endpoint principal para resolver el problema matemático de Codeforces 1374A.
     * 
     * Recibe tres parámetros (x, y, n) y calcula el máximo entero k que cumple
     * las condiciones: 0 ≤ k ≤ n y k mod x = y.
     * 
     * Las validaciones de entrada se realizan automáticamente mediante Bean Validation:
     * - x debe estar entre 2 y 10^9
     * - y debe estar entre 0 y ser menor que x
     * - n debe estar entre 1 y 10^9
     * 
     * Validaciones de negocio adicionales se realizan en el handler/service.
     * 
     * @param request Objeto que contiene los parámetros x, y, n validados
     * @return ResponseEntity con el resultado calculado y los parámetros originales
     * @throws IllegalArgumentException si y >= x (validación de negocio)
     */
    @PostMapping("/solve")
    @Operation(
        summary = "Resolver problema matemático",
        description = "Encuentra el máximo entero k tal que 0 ≤ k ≤ n y k mod x = y"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Problema resuelto exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MathProblemResponse.class),
                examples = @ExampleObject(
                    value = "{\"result\": 12339, \"x\": 7, \"y\": 5, \"n\": 12345}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Parámetros inválidos",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"mensaje\": \"y debe ser menor que x\"}"
                )
            )
        )
    })
    public ResponseEntity<MathProblemResponse> solveMathProblem(
        @Valid @RequestBody MathProblemRequest request) {
        
        // Delegar la lógica de negocio al handler correspondiente
        var response = this.mathProblemHandler.handle(request);
        return ResponseEntity.ok(response);
    }
} 
