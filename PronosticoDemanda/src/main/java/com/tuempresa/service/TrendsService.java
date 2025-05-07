package com.tuempresa.service;

import com.tuempresa.model.Trend;
import com.tuempresa.repository.ProductRepository;
import com.tuempresa.repository.TrendRepository;
import com.tuempresa.webclient.TrendDto; // DTO para deserializar respuesta
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrendsService {

    private final WebClient webClient;
    private final ProductRepository productRepo;
    private final TrendRepository trendRepo;

    public TrendsService(
            WebClient.Builder webClientBuilder,
            ProductRepository productRepo,
            TrendRepository trendRepo,
            @Value("${app.trends.api-url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
        this.productRepo = productRepo;
        this.trendRepo = trendRepo;
    }

    // Tarea programada que se ejecuta cada 'rate-ms' (configurado en application.properties)
    @Scheduled(fixedRateString = "${app.trends.rate-ms}")
    public void fetchAndStoreTrends() {
        // 1. Llamada HTTP al API de tendencias
        List<TrendDto> trends = webClient.get()
                .uri("/trending")
                .retrieve()
                .bodyToFlux(TrendDto.class)
                .collectList()
                .block();

        // 2. Para cada resultado, mapeamos al ID interno y guardamos en BD
        trends.forEach(dto -> {
            Long productId = mapToProductId(dto.getName());
            if (productId != null) {
                Trend t = new Trend();
                t.setProductId(productId);
                t.setScore(dto.getScore());
                t.setTimestamp(LocalDateTime.now());
                trendRepo.save(t);
            }
        });
    }

    // Lógica para encontrar qué producto interno corresponde al nombre de tendencia
    private Long mapToProductId(String trendName) {
        String norm = trendName.trim().toLowerCase();
        return productRepo.findByNameIgnoreCase(norm)
                .or(() -> productRepo.findFirstByKeywordsContaining(norm))
                .map(p -> p.getId())
                .orElse(null);
    }
}