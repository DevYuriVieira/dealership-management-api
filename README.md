# 🚗 Dealership Management API - Serratec

<div align="center">

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3+-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16+-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-REST-informational?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Status](https://img.shields.io/badge/Status-Completed-success?style=for-the-badge)

</div>

---

# 🇺🇸 English Version

# 📋 Table of Contents

- [About the Project](#-about-the-project)
- [Core Features](#-core-features)
- [Project Structure](#-project-structure)
- [Architecture and Technologies](#-architecture-and-technologies)
- [Technical Highlights](#-technical-highlights)
- [API Endpoints](#-api-endpoints)
- [Swagger Documentation](#-swagger-documentation)
- [How to Run](#-how-to-run)
- [Developer](#-developer)

---

# 📌 About the Project

This project consists of a **RESTful API** developed using **Java 17**, **Spring Boot 3**, and **PostgreSQL** for managing a modern vehicle dealership system.

The API manages the complete lifecycle of:

- Clients
- Vehicles
- Inventory relationships

The system ensures strong data integrity through:

- Advanced validations
- Centralized exception handling
- Layered architecture
- DTO pattern using Java Records

The project was built following backend best practices focused on:

- **Fail-Fast Validation**
- **Separation of Concerns**
- **Clean Architecture Principles**
- **Robust Error Handling**

Providing a scalable and organized backend foundation.

---

# 🎯 Core Features

## 👤 Client Management

- Create clients
- Search clients by CPF
- Search clients by partial name
- Delete clients
- Unique CPF validation
- Unique email validation

---

## 🚘 Vehicle Management

- Create vehicles
- Update vehicles
- Delete vehicles
- Search by:
    - Plate
    - Brand
    - Model

---

## 🛡️ Advanced Validations

- CPF Regex validation
- Unique database constraints
- Manufacturing year validation
- Required field validation
- Business rule validations

---

# 📁 Project Structure

```text
📦 dealership-api
 ┣ 📂 src/main/java/com/dealership/api
 ┃ ┣ 📂 controller
 ┃ ┃ ┣ 📜 ClientController.java
 ┃ ┃ ┗ 📜 VehicleController.java
 ┃ ┣ 📂 entity
 ┃ ┃ ┣ 📜 Client.java
 ┃ ┃ ┗ 📜 Vehicle.java
 ┃ ┣ 📂 exception
 ┃ ┃ ┣ 📜 DuplicateResourceException.java
 ┃ ┃ ┣ 📜 ErrorResponse.java
 ┃ ┃ ┣ 📜 GlobalExceptionHandler.java
 ┃ ┃ ┗ 📜 ResourceNotFoundException.java
 ┃ ┣ 📂 model
 ┃ ┃ ┣ 📜 ClientRequest.java
 ┃ ┃ ┣ 📜 ClientResponse.java
 ┃ ┃ ┣ 📜 VehicleRequest.java
 ┃ ┃ ┗ 📜 VehicleResponse.java
 ┃ ┣ 📂 repository
 ┃ ┃ ┣ 📜 ClientRepository.java
 ┃ ┃ ┗ 📜 VehicleRepository.java
 ┃ ┣ 📂 service
 ┃ ┃ ┣ 📜 ClientService.java
 ┃ ┃ ┗ 📜 VehicleService.java
 ┃ ┗ 📜 ApiApplication.java
 ┣ 📂 src/main/resources
 ┃ ┗ 📜 application.properties
 ┣ 📜 pom.xml
 ┗ 📜 README.md
```

---

# 🏗️ Architecture and Technologies

## 📌 Main Layers

### Controller
Handles HTTP requests and request validation using `@Valid`.

### Service
Responsible for:
- Business rules
- Database validations
- Mapping logic
- Exception handling

### Repository
Spring Data JPA interfaces responsible for persistence.

### DTO / Model
Separates API payloads from database entities using Java Records.

---

# ⚙️ Tech Stack

- Java 17
- Spring Boot 3
- PostgreSQL
- Spring Data JPA
- Maven
- Swagger / OpenAPI
- Jakarta Validation

---

# ⚙️ Technical Highlights

# 🛡️ Jakarta Validation (Fail-Fast)

Extensive usage of:

- `@Pattern`
- `@NotBlank`
- `@Min`
- `@Max`

Blocking invalid requests before they reach the business layer.

---

# 🔄 Centralized Exception Handling

Global exception management through `@ControllerAdvice`.

### HTTP Status Handling

| Status | Description |
|---|---|
| 400 | Invalid request data |
| 404 | Resource not found |
| 409 | Duplicate resource conflict |

Standardized JSON responses using `ErrorResponse`.

---

# 📦 DTO Pattern

Implementation using Java Records:

- Immutable payloads
- Safer API contracts
- Entity encapsulation
- Cleaner architecture

---

# 📡 API Endpoints

## 👤 Clients

| Method | Endpoint | Description |
|---|---|---|
| POST | `/clients` | Create client |
| GET | `/clients` | List/Search clients |
| GET | `/clients/{id}` | Get client by ID |
| DELETE | `/clients/{id}` | Delete client |

---

## 🚘 Vehicles

| Method | Endpoint | Description |
|---|---|---|
| POST | `/vehicles` | Create vehicle |
| GET | `/vehicles` | List/Search vehicles |
| PUT | `/vehicles/{id}` | Update vehicle |
| DELETE | `/vehicles/{id}` | Delete vehicle |

---

# 📚 Swagger Documentation

The API includes complete Swagger/OpenAPI documentation.

## Swagger UI

```bash
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI JSON

```bash
http://localhost:8080/v3/api-docs
```

---

# 🚀 How to Run

# ✅ Prerequisites

- Java JDK 17+
- PostgreSQL installed and running
- Maven installed

---

# 📥 Clone Repository

```bash
git clone https://github.com/DevYuriVieira/Spring-Boot-Dealership.git
```

---

# ⚙️ Configure Database

Edit:

```bash
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dealership_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶️ Run Application

```bash
mvn spring-boot:run
```

---

# 🌐 Access API

```bash
http://localhost:8080
```

Swagger:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 👨‍💻 Developer

Project developed as the individual evaluation for the API Module — Serratec Software ICT Residency 2026.1.

| Developer | GitHub |
|---|---|
| Yuri Vieira | https://github.com/DevYuriVieira |

---

---

# 🇧🇷 Versão em Português

# 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades Principais](#-funcionalidades-principais)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Arquitetura e Tecnologias](#-arquitetura-e-tecnologias)
- [Destaques Técnicos](#-destaques-técnicos)
- [Endpoints da API](#-endpoints-da-api)
- [Documentação Swagger](#-documentação-swagger)
- [Como Executar](#-como-executar)
- [Desenvolvedor](#-desenvolvedor)

---

# 📌 Sobre o Projeto

Este projeto consiste em uma API RESTful desenvolvida utilizando:

- Java 17
- Spring Boot 3
- PostgreSQL

Para gerenciamento de uma concessionária de veículos moderna.

O sistema foi construído para gerenciar:

- Clientes
- Veículos
- Relacionamentos entre entidades

Garantindo:

- Integridade de dados
- Validações avançadas
- Tratamento centralizado de exceções
- Arquitetura em camadas

A arquitetura prioriza:

- **Validação Fail-Fast**
- **Separação de Responsabilidades**
- **Boas práticas de Backend**
- **DTO Pattern com Java Records**

---

# 🎯 Funcionalidades Principais

## 👤 Gestão de Clientes

- Cadastro de clientes
- Busca por CPF
- Busca por nome parcial
- Remoção de clientes
- Validação de CPF único
- Validação de e-mail único

---

## 🚘 Gestão de Veículos

- Cadastro de veículos
- Atualização de veículos
- Remoção de veículos
- Busca por:
    - placa
    - marca
    - modelo

---

## 🛡️ Validações Avançadas

- Regex de CPF
- Restrições únicas no banco
- Validação de ano
- Campos obrigatórios
- Regras de negócio

---

# 📁 Estrutura do Projeto

```text
📦 dealership-api
 ┣ 📂 src/main/java/com/dealership/api
 ┃ ┣ 📂 controller
 ┃ ┃ ┣ 📜 ClientController.java
 ┃ ┃ ┗ 📜 VehicleController.java
 ┃ ┣ 📂 entity
 ┃ ┃ ┣ 📜 Client.java
 ┃ ┃ ┗ 📜 Vehicle.java
 ┃ ┣ 📂 exception
 ┃ ┃ ┣ 📜 DuplicateResourceException.java
 ┃ ┃ ┣ 📜 ErrorResponse.java
 ┃ ┃ ┣ 📜 GlobalExceptionHandler.java
 ┃ ┃ ┗ 📜 ResourceNotFoundException.java
 ┃ ┣ 📂 model
 ┃ ┃ ┣ 📜 ClientRequest.java
 ┃ ┃ ┣ 📜 ClientResponse.java
 ┃ ┃ ┣ 📜 VehicleRequest.java
 ┃ ┃ ┗ 📜 VehicleResponse.java
 ┃ ┣ 📂 repository
 ┃ ┃ ┣ 📜 ClientRepository.java
 ┃ ┃ ┗ 📜 VehicleRepository.java
 ┃ ┣ 📂 service
 ┃ ┃ ┣ 📜 ClientService.java
 ┃ ┃ ┗ 📜 VehicleService.java
 ┃ ┗ 📜 ApiApplication.java
 ┣ 📂 src/main/resources
 ┃ ┗ 📜 application.properties
 ┣ 📜 pom.xml
 ┗ 📜 README.md
```

---

# 🏗️ Arquitetura e Tecnologias

## 📌 Principais Camadas

### Controller
Gerencia requisições HTTP e validações estruturais.

### Service
Centraliza:
- regras de negócio
- validações
- lógica da aplicação

### Repository
Persistência utilizando Spring Data JPA.

### DTO / Model
Separação entre entidades internas e payloads da API.

---

# ⚙️ Stack Tecnológica

- Java 17
- Spring Boot 3
- PostgreSQL
- Maven
- Spring Data JPA
- Swagger / OpenAPI
- Jakarta Validation

---

# ⚙️ Destaques Técnicos

# 🛡️ Jakarta Validation

Uso extensivo de:

- `@Pattern`
- `@NotBlank`
- `@Min`
- `@Max`

Bloqueando dados inválidos antes da camada de negócio.

---

# 🔄 Tratamento Global de Exceções

Implementado com `@ControllerAdvice`.

| HTTP Status | Descrição |
|---|---|
| 400 | Dados inválidos |
| 404 | Recurso não encontrado |
| 409 | Conflito de recurso duplicado |

Padronização de respostas JSON utilizando `ErrorResponse`.

---

# 📦 Padrão DTO

Uso de Java Records para:

- Imutabilidade
- Segurança de payload
- Encapsulamento
- Desacoplamento

---

# 📡 Endpoints da API

## 👤 Clientes

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/clients` | Cadastrar cliente |
| GET | `/clients` | Buscar/Listar clientes |
| GET | `/clients/{id}` | Buscar cliente por ID |
| DELETE | `/clients/{id}` | Remover cliente |

---

## 🚘 Veículos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/vehicles` | Cadastrar veículo |
| GET | `/vehicles` | Buscar/Listar veículos |
| PUT | `/vehicles/{id}` | Atualizar veículo |
| DELETE | `/vehicles/{id}` | Remover veículo |

---

# 📚 Documentação Swagger

A API possui documentação completa via Swagger/OpenAPI.

## Swagger UI

```bash
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI JSON

```bash
http://localhost:8080/v3/api-docs
```

---

# 🚀 Como Executar

# ✅ Pré-requisitos

- Java JDK 17+
- PostgreSQL ativo
- Maven instalado

---

# 📥 Clone o Repositório

```bash
git clone https://github.com/DevYuriVieira/Spring-Boot-Dealership.git
```

---

# ⚙️ Configure o Banco

Arquivo:

```bash
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dealership_db
spring.datasource.username=seu_usuario_db
spring.datasource.password=sua_senha_db

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶️ Execute a Aplicação

```bash
mvn spring-boot:run
```

---

# 🌐 Acesse a API

```bash
http://localhost:8080
```

Swagger:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# 👨‍💻 Desenvolvedor

Projeto desenvolvido como avaliação individual do módulo de API — Residência TIC Software Serratec 2026.1.

| Desenvolvedor | GitHub |
|---|---|
| Yuri Vieira | https://github.com/DevYuriVieira |
