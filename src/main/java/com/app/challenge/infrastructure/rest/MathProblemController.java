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
 * Controller REST para resolver problemas matemáticos de Codeforces.
 *
 * Este controller expone endpoints para la resolución del problema 1374A
 * de Codeforces mediante API REST, siguiendo los principios de arquitectura
 * hexagonal donde actúa como un puerto de entrada (inbound adapter).
 *
 * Responsabilidades:
 * - Exponer endpoints HTTP para la funcionalidad de negocio
 * - Validar entrada mediante Bean Validation
 * - Delegar procesamiento al handler de aplicación
 * - Manejar responses HTTP apropiados
 *
 * @author Adolfo Villanueva
 * @since 2024-06-26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/math")
@RequiredArgsConstructor
@Tag(name = "Math Problem", description = "API para resolver problemas matemáticos de Codeforces")
public class MathProblemController {

    /**
     * Handler de aplicación para procesar problemas matemáticos.
     */
    private final MathProblemHandler mathProblemHandler;

    /**
     * Resuelve el problema matemático de Codeforces 1374A.
     *
     * Endpoint principal que recibe los parámetros x, y, n y retorna el máximo
     * valor k tal que 0 ≤ k ≤ n y k mod x = y.
     *
     * Validaciones automáticas:
     * - Parámetros obligatorios (NotNull)
     * - Rangos de valores según especificaciones del problema
     * - Formato JSON válido
     *
     * @param request DTO con parámetros validados x, y, n
     * @return ResponseEntity con resultado del cálculo y código HTTP 200
     */
    @PostMapping("/solve")
    @Operation(summary = "Resolver problema matemático", description = "Calcula el máximo k tal que 0 ≤ k ≤ n y k mod x = y")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Problema resuelto exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MathProblemResponse.class), examples = @ExampleObject(value = "{\"result\":12339,\"x\":7,\"y\":5,\"n\":12345}"))),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"mensaje\":\"x debe ser mayor o igual a 2\"}"))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"mensaje\":\"Error interno del servidor\"}")))
    })
    public ResponseEntity<MathProblemResponse> solveMathProblem(@Valid @RequestBody MathProblemRequest request) {
        MathProblemResponse response = this.mathProblemHandler.handle(request);
        return ResponseEntity.ok(response);
    }
} 
