package com.tuempresa.repository;


import com.tuempresa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Busca producto por nombre exacto ignorando mayúsculas
    Optional<Product> findByNameIgnoreCase(String name);

    // Busca el primer producto cuya cadena de keywords contiene el parámetro
    Optional<Product> findFirstByKeywordsContaining(String keyword);
}