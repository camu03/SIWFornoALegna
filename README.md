# Pizzeria Forno a Legna

Sito web per pizzeria realizzato con Spring Boot e PostgreSQL.

## Tecnologie utilizzate

### Backend
- **Spring Boot 3.2.0**
- **Spring Data JPA** - per la persistenza dei dati
- **PostgreSQL** - database relazionale
- **Thymeleaf** - template engine per le pagine web
- **Maven** - gestione delle dipendenze

### Frontend
- **HTML5**
- **CSS3**
- **Thymeleaf**

## Prerequisiti

- Java 17 o superiore
- Maven 3.6+
- PostgreSQL 12+
- pgAdmin (opzionale, per gestione database)

## Configurazione Database

1. Crea un database PostgreSQL chiamato `pizzeria`:
```sql
CREATE DATABASE pizzeria;
```

2. Modifica le credenziali in `src/main/resources/application.properties` se necessario:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pizzeria
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Avvio dell'applicazione

### Usando Maven

```bash
mvn spring-boot:run
```

### Usando Java

```bash
mvn clean package
java -jar target/forno-a-legna-0.0.1-SNAPSHOT.jar
```

L'applicazione sarà disponibile su: `http://localhost:8080`

## API Endpoints

### REST API per le Pizze

- `GET /api/pizze` - Ottieni tutte le pizze
- `GET /api/pizze/{id}` - Ottieni una pizza per ID
- `POST /api/pizze` - Crea una nuova pizza

### Pagine Web

- `GET /` - Homepage della pizzeria
- `GET /home` - Homepage della pizzeria

## Struttura del Progetto

```
src/
├── main/
│   ├── java/it/uniroma3/siw/fornialegna/
│   │   ├── FornoALegnaApplication.java
│   │   ├── controller/
│   │   │   ├── HomeController.java
│   │   │   └── PizzaController.java
│   │   ├── model/
│   │   │   └── Pizza.java
│   │   └── repository/
│   │       └── PizzaRepository.java
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css
│       │   ├── js/
│       │   └── images/
│       ├── templates/
│       │   └── index.html
│       └── application.properties
```

## Prossimi sviluppi

- [ ] Sistema di autenticazione utenti
- [ ] Gestione ordini online
- [ ] Area amministrativa per gestire il menu
- [ ] Sistema di prenotazione tavoli
- [ ] Integrazione pagamenti online
- [ ] Sezione recensioni clienti

## Autore

Roberto Camussi

## Licenza

Questo progetto è stato creato per scopi didattici.
