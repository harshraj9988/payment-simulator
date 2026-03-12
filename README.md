# Payment Service (Spring Boot)

A backend service that simulates a simple **payment system** with account management and transaction lifecycle handling.

This project is being built to explore **backend system design, REST APIs, and client–server architecture**, while integrating with a sample Android client.

🚧 **Currently in progress**

---

## 🚀 Features

### Current capabilities

- Create accounts via REST API
- Layered backend architecture (Controller → Service → Repository)
- DTO-based request/response handling
- Clean separation of domain entities
- RESTful API design using Spring Boot

### Upcoming features

- Payment transaction lifecycle management
- Idempotent payment APIs
- Transaction status tracking
- Retry logic for payment processing
- Android client integration

---

## 🧱 Architecture

The project follows a **layered architecture** commonly used in backend services.

```
Controller → Service → Repository → Database
```

### Layers

**Controller**
- Handles incoming HTTP requests
- Maps REST endpoints

**Service**
- Contains business logic
- Handles transaction lifecycle management

**Repository**
- Responsible for data persistence
- Uses Spring Data for database interaction

**DTO**
- Request/response objects
- Prevents exposing internal entities

---

## 📂 Project Structure

```
payment-simulator
│
├── controller
│   └── AccountController.java
│
├── service
│   └── AccountService.java
│
├── repository
│   └── AccountRepository.java
│
├── entity
│   └── Account.java
│
├── dto
│   └── AccountDto.java
│
├── mapper
│   └── AccountMapper.java
│
└── PaymentSimulatorApplication.java
```

---

## 📡 API Endpoints

### Create Account

```
POST /api/accounts
```

### Request Body

```json
{
  "name": "Harsh",
  "balance": 1000
}
```

### Response

```json
{
  "id": 1,
  "name": "Harsh",
  "balance": 1000
}
```

---

## ⚙️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- REST APIs

---

## 🧠 Concepts Explored

This project explores backend engineering concepts such as:

- REST API design
- Layered service architecture
- DTO pattern
- Request/response modeling
- Idempotent request handling *(planned)*
- Payment system lifecycle modeling

---

## 📱 Client Integration (Planned)

The backend will be integrated with a **sample Android client** to simulate a real client–server payment flow.

```
Android App → REST API → Payment Service
```

This will help explore:

- API reliability
- Request retries
- Transaction state synchronization

---

## 🛠️ Getting Started

### Clone the repository

```bash
git clone https://github.com/yourusername/payment-simulator.git
```

### Navigate to the project

```bash
cd payment-simulator
```

### Run the application

```bash
mvn spring-boot:run
```

The server will start on:

```
http://localhost:8080
```

---

## 📈 Future Improvements

Planned enhancements:

- Payment entity and transaction APIs
- Payment status tracking
- Idempotent payment requests
- Retry handling
- Integration testing
- Docker support

---

## 📚 Motivation

As a mobile engineer working primarily on **Android fintech applications**, I wanted to deepen my understanding of **backend systems and client–server architecture**.

This project is a learning initiative to explore:

- how backend payment services are structured
- how APIs manage transaction state
- how mobile clients interact with backend systems

---

## 🤝 Contributions

This project is currently a **personal learning project**, but feedback and suggestions are welcome.

---

## ⭐ If you find this project useful

Consider starring the repository!

---

## 👨‍💻 Author

**Harsh Raj**

Software Engineer