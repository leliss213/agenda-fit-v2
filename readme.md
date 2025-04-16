# Agenda Fit V2

API REST para gerenciar treinos e exercícios, construída com Spring Boot.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Web
- Spring Security
- JWT (JSON Web Tokens)
- Maven
- Docker
- Docker Compose
- MySQL

## Como Executar o Projeto

### Pré-requisitos

- Docker e Docker Compose instalados

### Passos

1.  Clone o repositório:
```
bash
    git clone <URL_DO_REPOSITORIO>
    cd agenda-fit-v2
    
```
2.  Construa e execute os containers Docker:
```
bash
    docker-compose up --build
    
```
Isso iniciará a API e um banco de dados MySQL.

3.  A API estará disponível em `http://localhost:8080`.

## Endpoints da API

### Autenticação

#### Registrar Usuário
```
POST /auth/register
```
**Request Body:**
```
json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "securepassword"
}
```
**Response (Success - 201 Created):**
```
json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```
**Response (Error - 400 Bad Request):**