# Quick Test Guide - User Service with H2

## Run Locally (No Database Required)

**Option 1: Using Maven**
```powershell
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

**Option 2: Using Java directly**
```powershell
mvn clean package -DskipTests
java -jar target/user-service-0.1.0-SNAPSHOT.jar --spring.profiles.active=local
```

## Test the Endpoints

Once running, the service will be at `http://localhost:8084`

### 1. Register a User
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/register" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"username":"testuser","password":"password123","email":"test@example.com"}'
```

### 2. Login
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/login" `
  -Method POST `
  -ContentType "application/json" `
  -Body '{"username":"testuser","password":"password123"}'
```

### 3. Get User by ID
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/1" -Method GET
```

### 4. Update User
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/1" `
  -Method PUT `
  -ContentType "application/json" `
  -Body '{"email":"newemail@example.com"}'
```

### 5. Check Username Availability
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/username/testuser" -Method GET
```

### 6. Delete User
```powershell
Invoke-RestMethod -Uri "http://localhost:8084/api/users/1" -Method DELETE
```

## Access H2 Console (Optional)

Visit: `http://localhost:8084/h2-console`

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave blank)

You can query the Users table:
```sql
SELECT * FROM Users;
```

## Notes

- Data is stored in-memory and will be lost when you stop the service
- The database schema is auto-created on startup (`ddl-auto=create-drop`)
- Switch back to Azure SQL by removing `--spring.profiles.active=local`
