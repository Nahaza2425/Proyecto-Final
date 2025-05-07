package com.tuempresa.service;

import com.tuempresa.model.Trend;
import com.tuempresa.repository.TrendRepository;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

    private final TrendRepository trendRepo;

    public ForecastService(TrendRepository trendRepo) {
        this.trendRepo = trendRepo;
    }

    /**
     * Calcula la predicción de demanda para un producto.
     * @param productId ID del producto
     * @param daysAhead número de días en el futuro
     * @return cantidad estimada de unidades
     */
    public double forecast(Long productId, int daysAhead) {
        // 1. Obtener la última puntuación de redes sociales
        double trendScore = trendRepo.findLatestScore(productId).orElse(0.0);
        // 2. Modelo simple: forecast = coef * trendScore
        //    En un MVP sólo usamos la señal social
        double coefficient = 10.0; // factor arbitrario para escalar
        return trendScore * coefficient * daysAhead;
    }
}