# 🚀 Geedzeco - Spring Boot REST API

Projekt startowy aplikacji Spring Boot z REST API dla zarządzania użytkownikami.

## 📋 Wymagania

- **Java 17+**
- **Maven 3.6+**
- **Git**

## 🛠️ Technologie

- Spring Boot 3.2.0
- Spring Data JPA
- Hibernate
- H2 Database (in-memory)
- Lombok
- Maven

## 📁 Struktura Projektu

```
Geedzeco/
├── src/
│   ├── main/
│   │   ├── java/com/geedzeco/
│   │   │   ├── GeedzecApplication.java      # Main app class
│   │   │   ├── controller/
│   │   │   │   └── UserController.java      # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── UserService.java         # Business logic
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java      # Data access layer
│   │   │   └── model/
│   │   │       └── User.java                # User entity
│   │   └── resources/
│   │       └── application.properties       # App configuration
│   └── test/                                # Tests
├── pom.xml                                  # Maven configuration
├── .gitignore                               # Git ignore rules
└── README.md                                # This file
```

## 🚀 Uruchomienie

### 1. Klonowanie repozytorium

```bash
git clone https://github.com/colchonero-cloud/Geedzeco.git
cd Geedzeco
```

### 2. Budowanie projektu

```bash
mvn clean install
```

### 3. Uruchomienie aplikacji

```bash
mvn spring-boot:run
```

Albo możesz uruchomić wbudowany JAR:

```bash
mvn package
java -jar target/geedzeco-1.0.0.jar
```

## 📡 REST API Endpoints

### Health Check

```http
GET /api/users/health/check
```

**Odpowiedź:**
```json
{
  "status": "UP",
  "message": "Geedzeco API is running!"
}
```

### Pobierz wszystkich użytkowników

```http
GET /api/users
```

**Odpowiedź:**
```json
[
  {
    "id": 1,
    "name": "Jan Kowalski",
    "email": "jan@example.com",
    "phone": "123456789",
    "address": "ul. Główna 1",
    "createdAt": 1704067200000,
    "updatedAt": 1704067200000
  }
]
```

### Pobierz użytkownika po ID

```http
GET /api/users/{id}
```

**Przykład:**
```http
GET /api/users/1
```

### Pobierz użytkownika po emailu

```http
GET /api/users/email/{email}
```

**Przykład:**
```http
GET /api/users/email/jan@example.com
```

### Utwórz nowego użytkownika

```http
POST /api/users
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Jan Kowalski",
  "email": "jan@example.com",
  "phone": "123456789",
  "address": "ul. Główna 1"
}
```

**Odpowiedź (201 Created):**
```json
{
  "id": 1,
  "name": "Jan Kowalski",
  "email": "jan@example.com",
  "phone": "123456789",
  "address": "ul. Główna 1",
  "createdAt": 1704067200000,
  "updatedAt": 1704067200000
}
```

### Aktualizuj użytkownika

```http
PUT /api/users/{id}
Content-Type: application/json
```

**Przykład:**
```http
PUT /api/users/1
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Jan Kowalski Junior",
  "phone": "987654321"
}
```

### Usuń użytkownika

```http
DELETE /api/users/{id}
```

**Przykład:**
```http
DELETE /api/users/1
```

**Odpowiedź:**
```json
{
  "message": "User deleted successfully"
}
```

## 🗄️ H2 Database Console

Po uruchomieniu aplikacji możesz uzyskać dostęp do konsoli H2:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (puste)

## 🧪 Testowanie API

### Używając cURL

**Utwórz użytkownika:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jan Kowalski",
    "email": "jan@example.com",
    "phone": "123456789",
    "address": "ul. Główna 1"
  }'
```

**Pobierz wszystkich użytkowników:**
```bash
curl http://localhost:8080/api/users
```

**Pobierz użytkownika po ID:**
```bash
curl http://localhost:8080/api/users/1
```

**Aktualizuj użytkownika:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jan Kowalski Junior"
  }'
```

**Usuń użytkownika:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

### Używając Postman

1. Zaimportuj poniższe żądania do Postman lub utwórz je ręcznie
2. Ustaw zmienną `base_url` na `http://localhost:8080`

## 📝 Konfiguracja

Plik `application.properties` zawiera:

- Port serwera: `8080`
- Baza danych: H2 in-memory
- JPA/Hibernate ustawienia
- Logi

## 🔧 Rozwinięcie Projektu

### Dodaj nową bazę danych

Zastąp H2 na MySQL/PostgreSQL:

1. W `pom.xml` dodaj driver:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. W `application.properties` zmień:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/geedzeco
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL10Dialect
```

### Dodaj walidację

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Dodaj dokumentację Swagger/OpenAPI

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>
```

## 📚 Przydatne Linki

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring REST API Guide](https://spring.io/guides/gs/rest-service/)
- [Lombok](https://projectlombok.org/)

## 📄 Licencja

Ten projekt jest dostępny na licencji MIT.

## 👨‍💻 Autor

Geedzeco - Spring Boot Starter Project

---

**Powodzenia w programowaniu! 🎉**