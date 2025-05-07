package com.tuempresa.repository;

import com.tuempresa.model.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TrendRepository extends JpaRepository<Trend, Long> {

    // Devuelve la puntuación más reciente para un producto dado
    @Query("SELECT t.score FROM Trend t WHERE t.productId = :pid ORDER BY t.timestamp DESC")
    Optional<Double> findLatestScore(@Param("pid") Long productId);
}