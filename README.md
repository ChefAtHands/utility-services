# Utility Services

This repository contains utility microservices for the ChefAtHands application.

## Services

### 1. Ingredient Service (Port 8081)

Manages global ingredient catalog and user-specific ingredient inventory (pantry).

#### **Global Ingredient Catalog Endpoints**

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api/ingredients` | Get all ingredients | - |
| `GET` | `/api/ingredients/{id}` | Get ingredient by ID | - |
| `GET` | `/api/ingredients/search?name={name}` | Search ingredients by name | - |
| `POST` | `/api/ingredients` | Create new ingredient | `{"name": "string", "category": "string"}` |
| `DELETE` | `/api/ingredients/{id}` | Delete ingredient | - |

**Example Requests:**

```bash
# Get all ingredients
curl http://localhost:8081/api/ingredients

# Get ingredient by ID
curl http://localhost:8081/api/ingredients/1

# Search ingredients by name
curl "http://localhost:8081/api/ingredients/search?name=garlic"

# Create new ingredient
curl -X POST http://localhost:8081/api/ingredients \
  -H "Content-Type: application/json" \
  -d '{"name": "Pineapple", "category": "Fruit"}'

# Delete ingredient
curl -X DELETE http://localhost:8081/api/ingredients/25
```

#### **User Ingredients (Pantry) Endpoints**

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api/users/{userId}/ingredients` | Get all ingredients for user | - |
| `POST` | `/api/users/{userId}/ingredients` | Add ingredient to user's pantry | `{"ingredientId": 4, "quantity": "2 pieces"}` |
| `PATCH` | `/api/users/{userId}/ingredients/{ingredientId}` | Update ingredient quantity | `{"quantity": "5 pieces"}` |
| `DELETE` | `/api/users/{userId}/ingredients/{ingredientId}` | Remove ingredient from user | - |

**Example Requests:**

```bash
# Get user 1's ingredients
curl http://localhost:8081/api/users/1/ingredients

# Add ingredient to user 1's pantry
curl -X POST http://localhost:8081/api/users/1/ingredients \
  -H "Content-Type: application/json" \
  -d '{"ingredientId": 4, "quantity": "2 pieces"}'

# Update quantity for user 1's ingredient 4
curl -X PATCH http://localhost:8081/api/users/1/ingredients/4 \
  -H "Content-Type: application/json" \
  -d '{"quantity": "5 pieces"}'

# Remove ingredient 4 from user 1's pantry
curl -X DELETE http://localhost:8081/api/users/1/ingredients/4
```

### 2. Logging Service (Port TBD)

*Coming soon...*

## Setup

### Prerequisites
- Java 17+
- Maven
- PostgreSQL (via Docker - see `db` repository)

### Run Ingredient Service

```bash
cd ingredient-service
mvn spring-boot:run
```

### Database Configuration

The ingredient service connects to the shared PostgreSQL database defined in the `db` repository.

**Connection Details:**
- URL: `jdbc:postgresql://localhost:5432/chefathands`
- Username: `cah_user`
- Password: `CahStrongPass123`

### Test Data

The database includes:
- **24 sample ingredients** (Mozzarella, Garlic, Chicken, etc.)
- **3 test users** (IDs: 1, 2, 3)
- **Pre-populated user ingredients** for testing

## Tech Stack

- **Spring Boot 2.7.14**
- **Spring Data JPA**
- **PostgreSQL**
- **Hibernate Validation**

## Database Schema

### Tables Used

- `ingredients` - Global ingredient catalog
- `user_ingredients` - User-specific ingredient inventory
- `users` - User accounts (managed by User Service)

Schema is managed centrally in the `db` repository using `schema.sql`.
