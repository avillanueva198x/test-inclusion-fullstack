package com.app.challenge.application.handler;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Handler para procesar solicitudes del problema matemático.
 *
 * @author Adolfo Villanueva
 * @since 2024-06-26
 * @version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MathProblemHandler {

    /**
     * Servicio de aplicación para resolver problemas matemáticos.
     */
    private final MathProblemService mathProblemService;

    /**
     * Procesa la solicitud del problema matemático.
     *
     * @param request parámetros del problema
     * @return respuesta con el resultado calculado
     */
    public MathProblemResponse handle(MathProblemRequest request) {
        log.info("Procesando problema matemático - x={}, y={}, n={}",
            request.x(), request.y(), request.n());

        MathProblemResponse response = this.mathProblemService.solveMathProblem(request);

        log.info("Resultado calculado: {}", response.result());
        return response;
    }
} 