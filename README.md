# utility-service

endpoints for Ingredient service: 

# === Ingredient Catalog ===

# 1. Get all ingredients
curl http://localhost:8081/api/ingredients

# 2. Get ingredient by ID
curl http://localhost:8081/api/ingredients/1

# 3. Search ingredients by name
curl "http://localhost:8081/api/ingredients/search?name=garlic"

# 4. Create new ingredient
curl -X POST http://localhost:8081/api/ingredients \
  -H "Content-Type: application/json" \
  -d '{"name": "Pineapple", "category": "Fruit"}'

# 5. Delete ingredient
curl -X DELETE http://localhost:8081/api/ingredients/25


# === User Ingredients (Pantry) ===

# 6. Get user 1's ingredients
curl http://localhost:8081/api/users/1/ingredients

# 7. Add ingredient to user 1's pantry
curl -X POST http://localhost:8081/api/users/1/ingredients \
  -H "Content-Type: application/json" \
  -d '{"ingredientId": 4, "quantity": "2 pieces"}'

# 8. Update quantity for user 1's ingredient 4
curl -X PATCH http://localhost:8081/api/users/1/ingredients/4 \
  -H "Content-Type: application/json" \
  -d '{"quantity": "5 pieces"}'

# 9. Remove ingredient 4 from user 1's pantry
curl -X DELETE http://localhost:8081/api/users/1/ingredients/4
