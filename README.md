# Payment Service (Spring Boot)

A backend service that simulates a simple **payment system** with account management and transaction lifecycle handling.

This project is being built to explore **backend system design, REST APIs, and client–server architecture and idempotent request handling**, while integrating with a sample Android client.

🚧 **Currently in progress**

---

## 🚀 Features

### Account Management

- Create accounts
- Fetch account details

### Transaction Processing

- Deposit money into an account
- Withdraw money from an account
- Transfer money between accounts

### Reliability & Safety

- Idempotent transaction APIs using idempotency keys
- Atomic transactions using Spring ```@Transactional```
- Centralized error handling via ```@ControllerAdvice```
- Validation using Jakarta Bean Validation
- Transaction ledger to persist financial history

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
│   └── TransactionController.java
│
├── service
│   └── impl
│   │  └── AccountServiceImpl.java
│   │  └── TransactionServiceImpl.java
│   │ 
│   └── AccountService.java
│   └── TransactionService.java
│
├── repository
│   └── AccountRepository.java
│   └── TransactionRepository.java
│
├── entity
│   └── Account.java
│   └── Transaction.java
│
├── dto
│   └── AccountDto.java
│   └── TransactionRequestDto.java
│   └── AmountRequestDto.java
│
├── mapper
│   └── AccountMapper.java
│   └── TransactionMapper.java
│
├── exception
│   └── GlobalExceptionHandler.java
│   └── AccountNotFoundException.java
│   └── DuplicateTransactionException.java
│   └── InvalidTransactionException.java
│   └── InsufficientFundsException.java
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

## 💰 Transaction APIs

### Deposit

```
PUT /api/transactions/deposit
```

### Request Body

```json
{
  "accountId": 1,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```

### Response

```json
{
  "id": 1,
  "accountId": 1,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```

---
### Withdraw

```
PUT /api/transactions/withdraw
```

### Request Body

```json
{
  "accountId": 1,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```

### Response

```json
{
  "id": 1,
  "accountId": 1,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```

---
### Transfer

```
PUT /api/transactions/transfer
```

### Request Body

```json
{
  "sourceAccountId": 1,
  "targetAccountId": 2,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```

### Response

```json
{
  "id": 1,
  "sourceAccountId": 1,
  "targetAccountId": 2,
  "amount": 500,
  "idempotencyKey": "txn-1"
}
```
---

### 🔐 Idempotent Transactions

All transaction APIs require an idempotency key.

If the same request is retried with the same key, the system detects it and prevents duplicate processing.

This is commonly used in payment systems to protect against network retries or client crashes.

---

### ⚠️ Error Handling

The service uses centralized exception handling with @ControllerAdvice.

Errors are returned in a consistent structure:

```json
{
  "timestamp": "2026-03-13T21:42:11",
  "status": 400,
  "error": "Validation Error",
  "message": {
    "amount": "must be greater than 0"
  },
  "path": "/api/transactions/transfer"
}
```
Handled scenarios include:
- Validation errors
- Account not found
- Duplicate transactions
- Insufficient funds
- Invalid transaction requests

---

## ⚙️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- Maven
- REST APIs
- MySQL / H2

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