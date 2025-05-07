fashion-forecast/
├── pom.xml
├── Dockerfile
├── README.md
└── src
├── main
│   ├── java
│   │   └── com/tuempresa/fashionforecast
│   │       ├── ForecastApplication.java       // Clase principal con main()
│   │       ├── model
│   │       │   ├── Product.java               // Entidad JPA de catálogo
│   │       │   └── Trend.java                 // Entidad JPA de tendencias sociales
│   │       ├── repository
│   │       │   ├── ProductRepository.java     // JpaRepository<Product, Long>
│   │       │   └── TrendRepository.java       // JpaRepository<Trend, Long>
│   │       ├── service
│   │       │   ├── TrendsService.java         // @Scheduled fetch y guardado de tendencias
│   │       │   └── ForecastService.java       // Lógica de cálculo de pronóstico
│   │       └── controller
│   │           └── ForecastController.java    // @RestController con endpoint /forecast
│   └── resources
│       └── application.yml                   // Configuración de MySQL y scheduling
└── test
└── java
└── com/tuempresa/fashionforecast
├── ProductRepositoryTest.java
├── TrendRepositoryTest.java
├── TrendsServiceTest.java
├── ForecastServiceTest.java
└── ForecastControllerTest.java