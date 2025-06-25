package com.app.challenge.domain.usecase;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementación del caso de uso para resolver el problema matemático de Codeforces 1374A.
 *
 * @author Adolfo Villanueva
 * @since 2024-06-26
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MathProblemUseCase implements MathProblemService {

    /**
     * Resuelve el problema matemático encontrando el máximo k tal que 0 ≤ k ≤ n y k mod x = y.
     *
     * @param request parámetros x, y, n validados
     * @return respuesta con el resultado calculado
     * @throws IllegalArgumentException si y >= x
     */
    @Override
    public MathProblemResponse solveMathProblem(MathProblemRequest request) {
        log.info("Resolviendo problema matemático con x={}, y={}, n={}", request.x(), request.y(), request.n());

        long result = calculateMaxK(request.x(), request.y(), request.n());

        if (result != -1 && request.y() >= request.x()) {
            throw new IllegalArgumentException("y debe ser menor que x");
        }

        log.info("Resultado calculado: {}", result);
        return new MathProblemResponse(result, request.x(), request.y(), request.n());
    }

    /**
     * Calcula el máximo k usando la fórmula: k = y + floor((n-y)/x) * x.
     *
     * @param x base del módulo
     * @param y residuo deseado
     * @param n límite superior
     * @return máximo k válido, o -1 si no existe
     */
    private long calculateMaxK(long x, long y, long n) {
        if (y > n) {
            log.debug("No hay solución: y ({}) > n ({})", y, n);
            return -1;
        }

        long k = y + ((n - y) / x) * x;
        log.debug("Calculado k = {}", k);
        return k;
    }
} 
