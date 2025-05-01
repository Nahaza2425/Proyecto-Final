package com.tuempresa.model;

import jakarta.persistence.*;


//@Entity significa que una clase esta mapeada en la table de la DB
@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esto hace que el valor de la PK se genera automaticamente en BD
    private Long id;
    //Este es el identificador unico del producto

    @Column(nullable = false, unique = true) //Esta columan no puede estar vacia y titne que ser unico
    private String sku;
    //Codigo de referencia de Inditex

    @Column(nullable = false)
    private String name;
    //Nombre oficila del producto

    @Column(length = 512)
    private String keywords;
    //Lista de palabras clave/sinonimos

    //Gettes y setters...
    public long getId(){
        return id;
    }
    public void  setId(Long id){
        this.id = id;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getKeywords(){
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
