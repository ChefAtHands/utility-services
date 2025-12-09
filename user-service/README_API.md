# User Service API

REST API for user management with registration, authentication, and profile operations.

## Base URL
```
http://localhost:8080/api/users
```

## Endpoints

### 1. Register User
Create a new user account.

**Endpoint:** `POST /api/users/register`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123",
  "email": "john@example.com"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Errors:**
- `409 Conflict` - Username already exists
- `400 Bad Request` - Validation failed

---

### 2. Login
Authenticate a user (verify credentials).

**Endpoint:** `POST /api/users/login`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Response:** `200 OK`
```json
{
  "userId": 1,
  "username": "john_doe",
  "message": "Login successful"
}
```

**Errors:**
- `401 Unauthorized` - Invalid username or password

---

### 3. Get User Profile
Retrieve user details by ID.

**Endpoint:** `GET /api/users/{id}`

**Response:** `200 OK`
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Errors:**
- `404 Not Found` - User not found

---

### 4. Update User Profile
Update user information (all fields optional).

**Endpoint:** `PUT /api/users/{id}`

**Request Body:**
```json
{
  "username": "john_updated",
  "email": "newemail@example.com",
  "password": "newPassword456"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "username": "john_updated",
  "email": "newemail@example.com"
}
```

**Errors:**
- `404 Not Found` - User not found
- `409 Conflict` - New username already exists
- `400 Bad Request` - Validation failed

---

### 5. Delete User
Delete a user account.

**Endpoint:** `DELETE /api/users/{id}`

**Response:** `204 No Content`

**Errors:**
- `404 Not Found` - User not found

---

### 6. Check Username Availability
Check if a username is already taken.

**Endpoint:** `GET /api/users/username/{username}`

**Response:** `200 OK`
```json
true
```
or
```json
false
```

---

## Error Response Format

All errors follow a consistent format:

**Validation Error (400):**
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "username": "Username must be between 3 and 50 characters",
    "email": "Email must be valid"
  },
  "timestamp": "2025-12-09T10:30:00"
}
```

**Standard Error:**
```json
{
  "status": 404,
  "message": "User not found with ID: 5",
  "timestamp": "2025-12-09T10:30:00"
}
```

---

## Validation Rules

### Registration
- **username**: 3-50 characters, required
- **password**: minimum 6 characters, required
- **email**: valid email format, required

### Update
- All fields are optional
- Same validation rules as registration when provided

---

## Security

- Passwords are hashed using SHA-256 with random salt
- Passwords are never returned in responses
- For production: Consider upgrading to BCrypt or Argon2

---

## Running the Service

1. **Configure Database:**
   Edit `src/main/resources/application.properties` with your database credentials.

2. **Build:**
   ```bash
   mvn clean install
   ```

3. **Run:**
   ```bash
   mvn spring-boot:run
   ```

4. **Test:**
   ```bash
   curl -X POST http://localhost:8080/api/users/register \
     -H "Content-Type: application/json" \
     -d '{"username":"testuser","password":"test123","email":"test@example.com"}'
   ```

---

## Database Schema

The service expects a `Users` table with the following structure:

```sql
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(255) NOT NULL UNIQUE,
    passwordHash NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL
);
```
