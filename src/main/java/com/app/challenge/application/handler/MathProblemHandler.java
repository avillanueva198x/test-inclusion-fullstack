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
 * - Coordinar las validaciones y lógica de negocio
 * - Delegar el procesamiento al servicio de dominio
 * - Manejar el logging y trazabilidad
 * 
 * @author Adolfo Villanueva
 * @version 1.0
 * @since 2024-06-26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MathProblemHandler {

    /** Servicio de dominio que contiene la lógica de negocio para resolver el problema */
    private final MathProblemService mathProblemService;

    /**
     * Maneja la petición de resolución del problema matemático.
     * 
     * Este método actúa como orquestador, recibiendo la petición desde el controller
     * y delegando el procesamiento al servicio de dominio correspondiente.
     * 
     * Se incluye logging para trazabilidad y monitoreo de las operaciones.
     * 
     * @param request Objeto que contiene los parámetros x, y, n ya validados
     * @return MathProblemResponse con el resultado calculado y parámetros originales
     * @throws IllegalArgumentException si y >= x (validación de negocio)
     */
    public MathProblemResponse handle(MathProblemRequest request) {
        log.info("Procesando solicitud de problema matemático con parámetros: x={}, y={}, n={}", 
                 request.x(), request.y(), request.n());
        
        // Delegar la lógica de negocio al servicio de dominio
        var response = this.mathProblemService.solveMathProblem(request);
        
        log.info("Problema matemático resuelto exitosamente. Resultado: {}", response.result());
        
        return response;
    }
} 