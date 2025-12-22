package com.chefathands.history.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/history")
public class HistoryController {
    
    // Mock: Always returns a single history entry
    @GetMapping
    public ResponseEntity<String> getHistory(@PathVariable String userId) {
        String mockResponse = """
            [
                {
                    "id": "history-1",
                    "userId": "user123",
                    "recipeId": "recipe-101",
                    "recipeName": "Spaghetti Carbonara",
                    "recipeImageUrl": "https://example.com/carbonara.jpg",
                    "viewedAt": "2025-12-22T10:30:00"
                }
            ]
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @PostMapping
    public ResponseEntity<String> addHistory(@PathVariable String userId, @RequestBody String body) {
        String mockResponse = """
            {
                "id": "history-new",
                "userId": "user123",
                "recipeId": "recipe-999",
                "recipeName": "Mock Recipe",
                "recipeImageUrl": "https://example.com/mock.jpg",
                "viewedAt": "2025-12-22T12:00:00"
            }
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> removeHistory(@PathVariable String userId, @PathVariable String recipeId) {
        return ResponseEntity.noContent().build();
    }
    
    // Mock: Always returns true
    @GetMapping("/{recipeId}/exists")
    public ResponseEntity<String> isInHistory(@PathVariable String userId, @PathVariable String recipeId) {
        return ResponseEntity.ok("true");
    }
}
