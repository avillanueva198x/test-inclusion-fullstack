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

@RestController
@RequestMapping("/api/v1/math")
@RequiredArgsConstructor
@Tag(name = "Problema Matemático", description = "Resolución del problema de Codeforces 1374A")
public class MathProblemController {

    private final MathProblemHandler mathProblemHandler;

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
        var response = this.mathProblemHandler.handle(request);
        return ResponseEntity.ok(response);
    }
} 
