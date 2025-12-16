package com.chefathands.ingredient.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.service.UserIngredientService;

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
    
    @DeleteMapping("/{userIngredientId}")
    public ResponseEntity<Void> removeIngredient(
            @PathVariable Integer userId,
            @PathVariable Integer userIngredientId) {
        service.removeUserIngredient(userId, userIngredientId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{userIngredientId}")
    public ResponseEntity<UserIngredient> updateQuantity(
            @PathVariable Integer userId,
            @PathVariable Integer userIngredientId,
            @RequestBody Map<String, Object> request) {
        String quantity = (String) request.get("quantity");
        UserIngredient updated = service.updateQuantity(userId, userIngredientId, quantity);
        return ResponseEntity.ok(updated);
    }
}