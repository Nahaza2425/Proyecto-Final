package com.tuempresa.controller;

import com.tuempresa.service.ForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    /**
     * Endpoint para obtener la demanda estimada.
     * Ejemplo: GET /forecast?productId=1&daysAhead=7
     */
    @GetMapping
    public ResponseEntity<ForecastResponse> getForecast(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int daysAhead) {

        double prediction = forecastService.forecast(productId, daysAhead);
        ForecastResponse resp = new ForecastResponse(productId, daysAhead, prediction);
        return ResponseEntity.ok(resp);
    }

    // DTO interno para la respuesta
    public static class ForecastResponse {
        public Long productId;
        public int daysAhead;
        public double predictedQuantity;

        public ForecastResponse(Long productId, int daysAhead, double predictedQuantity) {
            this.productId = productId;
            this.daysAhead = daysAhead;
            this.predictedQuantity = predictedQuantity;
        }
    }
}