package com.app.challenge.application.handler;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Handler de aplicación para el problema matemático de Codeforces 1374A.
 *
 * En la arquitectura hexagonal, este handler actúa como el punto de entrada
 * de la capa de aplicación, orquestando las operaciones necesarias para
 * resolver el problema matemático.
 *
 * Su responsabilidad principal es:
 * - Recibir las peticiones del controller (puerto de entrada)
 * - Coordinar la ejecución del caso de uso correspondiente
 * - Retornar la respuesta formateada al controller
 * - Manejar el logging de las operaciones
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
     * Maneja la petición de resolución del problema matemático.
     *
     * Este método actúa como el punto de entrada principal del handler,
     * coordinando la ejecución del caso de uso y el manejo de logs.
     *
     * Flujo de ejecución:
     * 1. Log de inicio de operación
     * 2. Delegación al servicio de aplicación
     * 3. Log de finalización exitosa
     * 4. Retorno de respuesta al controller
     *
     * @param request DTO con los parámetros del problema matemático
     * @return MathProblemResponse con el resultado y parámetros originales
     */
    public MathProblemResponse handle(MathProblemRequest request) {
        log.info("Iniciando resolución de problema matemático - x={}, y={}, n={}",
            request.x(), request.y(), request.n());

        MathProblemResponse response = this.mathProblemService.solveMathProblem(request);

        log.info("Problema matemático resuelto exitosamente - resultado: {}",
            response.result());

        return response;
    }
} 