package com.chefathands.ingredient.controller;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.service.UserIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/ingredients")
public class UserIngredientController {
    
    private final UserIngredientService service;
    
    public UserIngredientController(UserIngredientService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<UserIngredient> getUserIngredients(@PathVariable Integer userId) {
        return service.getUserIngredients(userId);
    }
    
    @PostMapping
    public ResponseEntity<UserIngredient> addIngredient(
            @PathVariable Integer userId,
            @RequestBody UserIngredient userIngredient) {
        userIngredient.setUserId(userId);
        UserIngredient saved = service.addUserIngredient(userIngredient);
        return ResponseEntity.created(URI.create("/api/users/" + userId + "/ingredients/" + saved.getId()))
            .body(saved);
    }
    
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> removeIngredient(
            @PathVariable Integer userId,
            @PathVariable Integer ingredientId) {
        service.removeUserIngredient(userId, ingredientId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{ingredientId}")
    public ResponseEntity<UserIngredient> updateQuantity(
            @PathVariable Integer userId,
            @PathVariable Integer ingredientId,
            @RequestBody Map<String, Object> request) {
        String quantity = (String) request.get("quantity");
        UserIngredient updated = service.updateQuantity(userId, ingredientId, quantity);
        return ResponseEntity.ok(updated);
    }
}