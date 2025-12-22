package com.chefathands.favorites.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/favourites")
public class FavouritesController {
    
    // Mock: Always returns the same list of favourites
    @GetMapping
    public ResponseEntity<String> getFavourites(@PathVariable String userId) {
        String mockResponse = """
            [
                {
                    "id": "fav-1",
                    "userId": "user123",
                    "recipeId": "recipe-101",
                    "recipeName": "Spaghetti Carbonara",
                    "recipeImageUrl": "https://example.com/carbonara.jpg",
                    "addedAt": "2025-12-15T10:30:00"
                },
                {
                    "id": "fav-2",
                    "userId": "user123",
                    "recipeId": "recipe-202",
                    "recipeName": "Chicken Curry",
                    "recipeImageUrl": "https://example.com/curry.jpg",
                    "addedAt": "2025-12-16T14:20:00"
                },
                {
                    "id": "fav-3",
                    "userId": "user123",
                    "recipeId": "recipe-303",
                    "recipeName": "Chocolate Cake",
                    "recipeImageUrl": "https://example.com/cake.jpg",
                    "addedAt": "2025-12-17T09:15:00"
                }
            ]
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @PostMapping
    public ResponseEntity<String> addFavourite(@PathVariable String userId, @RequestBody String body) {
        String mockResponse = """
            {
                "id": "fav-new",
                "userId": "user123",
                "recipeId": "recipe-999",
                "recipeName": "Mock Recipe",
                "recipeImageUrl": "https://example.com/mock.jpg",
                "addedAt": "2025-12-21T12:00:00"
            }
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable String userId, @PathVariable String recipeId) {
        return ResponseEntity.noContent().build();
    }
    
    // Mock: Always returns true
    @GetMapping("/{recipeId}/exists")
    public ResponseEntity<String> isFavourite(@PathVariable String userId, @PathVariable String recipeId) {
        return ResponseEntity.ok("true");
    }
}
