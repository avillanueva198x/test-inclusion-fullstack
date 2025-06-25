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
 * Controller REST para resolver problemas matemáticos.
 *
 * @author Adolfo Villanueva
 * @since 2024-06-26
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/math")
@RequiredArgsConstructor
@Tag(name = "Math Problem", description = "API para resolver problemas matemáticos")
public class MathProblemController {

    /**
     * Handler de aplicación para procesar problemas matemáticos.
     */
    private final MathProblemHandler mathProblemHandler;

    /**
     * Resuelve el problema matemático de Codeforces 1374A.
     *
     * @param request parámetros x, y, n
     * @return resultado del cálculo
     */
    @PostMapping("/solve")
    @Operation(summary = "Resolver problema matemático", description = "Calcula el máximo k tal que 0 ≤ k ≤ n y k mod x = y")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Problema resuelto exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MathProblemResponse> solveMathProblem(@Valid @RequestBody MathProblemRequest request) {
        MathProblemResponse response = this.mathProblemHandler.handle(request);
        return ResponseEntity.ok(response);
    }
}
