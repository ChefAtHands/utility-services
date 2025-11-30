package com.chefathands.ingredient.controller;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.service.UserIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/ingredients")
public class UserIngredientController {
    
    private final UserIngredientService service;
    
    public UserIngredientController(UserIngredientService service) {
        this.service = service;
    }
    
    // GET /api/users/1/ingredients - Get all ingredients for user
    @GetMapping
    public List<UserIngredient> getUserIngredients(@PathVariable Integer userId) {
        return service.getUserIngredients(userId);
    }
    
    // POST /api/users/1/ingredients - Add ingredient to user
    @PostMapping
    public ResponseEntity<UserIngredient> addIngredient(
            @PathVariable Integer userId,
            @RequestBody UserIngredient userIngredient) {
        userIngredient.setUserId(userId);
        UserIngredient saved = service.addUserIngredient(userIngredient);
        return ResponseEntity.created(URI.create("/api/users/" + userId + "/ingredients/" + saved.getId()))
            .body(saved);
    }
    
    // DELETE /api/users/1/ingredients/5 - Remove ingredient from user
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> removeIngredient(
            @PathVariable Integer userId,
            @PathVariable Integer ingredientId) {
        service.removeUserIngredient(userId, ingredientId);
        return ResponseEntity.noContent().build();
    }
    
    // PATCH /api/users/1/ingredients/5 - Update quantity
    @PatchMapping("/{ingredientId}")
    public ResponseEntity<UserIngredient> updateQuantity(
            @PathVariable Integer userId,
            @PathVariable Integer ingredientId,
            @RequestBody UpdateQuantityRequest request) {
        UserIngredient updated = service.updateQuantity(userId, ingredientId, request.getQuantity());
        return ResponseEntity.ok(updated);
    }
    
    // Inner class for update request
    public static class UpdateQuantityRequest {
        private String quantity;
        
        public String getQuantity() { return quantity; }
        public void setQuantity(String quantity) { this.quantity = quantity; }
    }
}
