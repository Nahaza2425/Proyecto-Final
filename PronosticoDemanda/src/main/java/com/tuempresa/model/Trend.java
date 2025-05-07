package com.tuempresa.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity //Esta clase se va a mapera en la DB gracias a @Entity
@Table(name = "trend")
public class Trend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La Db se encarga de crear la PK
    private Long id;

    @Column(nullable = false)//Tiene que estar relllena si o si
    private Long productId;
    //FK del producto mapeado

    @Column(nullable = false)
    private LocalDateTime timestamp;
    //Momento de la captura de la tendencia

    // Getters y setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}

