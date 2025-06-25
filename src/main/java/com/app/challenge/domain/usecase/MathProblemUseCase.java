package com.app.challenge.domain.usecase;

import com.app.challenge.application.service.MathProblemService;
import com.app.challenge.domain.model.dto.request.MathProblemRequest;
import com.app.challenge.domain.model.dto.response.MathProblemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para resolver el problema matemático de Codeforces 1374A.
 *
 * Este caso de uso implementa el algoritmo para encontrar el máximo entero k
 * tal que cumple las condiciones: 0 ≤ k ≤ n y k mod x = y.
 *
 * El problema se resuelve usando la siguiente lógica:
 * - Si y > n, no existe solución válida (retorna -1)
 * - Si y ≤ n, entonces k = y + floor((n-y)/x) * x
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
     * Resuelve el problema matemático de Codeforces 1374A.
     *
     * Este método implementa el algoritmo principal para encontrar el máximo k
     * que satisface las condiciones del problema.
     *
     * Pasos del algoritmo:
     * 1. Aplicar el algoritmo de cálculo (que maneja el caso y > n internamente)
     * 2. Validar que y < x (constraint del problema) solo si hay una solución válida
     * 3. Retornar el resultado encapsulado en el DTO de respuesta
     *
     * @param request Objeto que contiene los parámetros x, y, n ya validados
     * @return MathProblemResponse con el resultado y parámetros originales
     * @throws IllegalArgumentException si y >= x (violación de constraint del problema)
     */
    @Override
    public MathProblemResponse solveMathProblem(MathProblemRequest request) {
        log.info("Resolviendo problema matemático con x={}, y={}, n={}", request.x(), request.y(), request.n());

        // Aplicar el algoritmo principal
        long result = calculateMaxK(request.x(), request.y(), request.n());

        // Validar constraint y < x solo si hay solución válida
        if (result != -1 && request.y() >= request.x()) {
            throw new IllegalArgumentException("y debe ser menor que x");
        }

        log.info("Resultado calculado: {}", result);
        return new MathProblemResponse(result, request.x(), request.y(), request.n());
    }

    /**
     * Algoritmo principal para calcular el máximo k.
     *
     * Implementa la fórmula matemática del problema de Codeforces 1374A:
     * - Si y > n: retorna -1 (no hay solución)
     * - Si y ≤ n: retorna y + floor((n-y)/x) * x
     *
     * La lógica se basa en encontrar el mayor múltiplo de x que, sumado a y,
     * no exceda n. Esto se logra calculando cuántos "saltos" de tamaño x
     * podemos dar desde y sin superar n.
     *
     * @param x base del módulo
     * @param y residuo deseado
     * @param n límite superior
     * @return máximo k tal que 0 ≤ k ≤ n y k mod x = y, o -1 si no existe
     */
    private long calculateMaxK(long x, long y, long n) {
        // Caso especial: si y > n, no hay solución válida
        if (y > n) {
            log.debug("No hay solución: y ({}) > n ({})", y, n);
            return -1;
        }

        // Calcular el máximo k usando la fórmula matemática
        // k = y + floor((n-y)/x) * x
        long k = y + ((n - y) / x) * x;

        log.debug("Calculado k = {} con fórmula: {} + (({} - {}) / {}) * {}",
            k, y, n, y, x, x);

        return k;
    }
} 
