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
## Workouts

### Add Workout

```
POST /api/workouts
```

**Request Body:**

```
json
{
"title": "My Workout",
"description": "A workout routine",
"date": "2024-01-15",
"hours": 1.5,
"workoutsExercises": [
{
"exerciseId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
"repetitions": 10,
"sets": 3,
"rest": 60
}
],
"type": 1,
"userId": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
}
```

**Response (Success - 201 Created):**

```
json
{
"id_workout": "d4e5f6a7-b8c9-1011-1213-141516171819",
"title": "My Workout",
"description": "A workout routine",
"workoutsExercises": [
{
"exerciseId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
"repetitions": 10,
"sets": 3,
"rest": 60
}
],
"user_id": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
}
```

### Update Workout

```
PATCH /api/workouts/{id_workout}
```

**Request Body:**

```
json
{
"title": "Updated Workout",
"description": "An updated workout routine",
"date": "2024-01-20",
"hours": 2.0,
"workoutsExercises": [
{
"exerciseId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
"repetitions": 12,
"sets": 4,
"rest": 45
}
],
"type": 2,
"userId": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
}
```

**Response (Success - 200 OK):**

```
json
{
"id_workout": "d4e5f6a7-b8c9-1011-1213-141516171819",
"title": "Updated Workout",
"description": "An updated workout routine",
"workoutsExercises": [
{
"exerciseId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
"repetitions": 12,
"sets": 4,
"rest": 45
}
],
"user_id": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
}
```

### Get Workouts by User

```
GET /api/workouts/user/{id_user}
```

**Response (Success - 200 OK):**

```
json
[
{
"id_workout": "d4e5f6a7-b8c9-1011-1213-141516171819",
"title": "My Workout",
"description": "A workout routine",
"workoutsExercises": [
{
"exerciseId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
"repetitions": 10,
"sets": 3,
"rest": 60
}
],
"user_id": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
},
{
"id_workout": "e5f6a7b8-c9d0-1112-1314-151617181920",
"title": "Another Workout",
"description": "Another workout routine",
"workoutsExercises": [
{
"exerciseId": "0e02b2c3-d479-47ac-b10b-58cc4372a567",
"repetitions": 15,
"sets": 3,
"rest": 60
}
],
"user_id": "a1b2c3d4-e5f6-7890-1234-567890abcdef"
}
]
```