package com.chefathands.ingredient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import com.chefathands.ingredient.service.IngredientService;
import com.chefathands.ingredient.model.Ingredient;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    
    private final IngredientService service;
    public IngredientController(IngredientService service) { this.service = service; }

    @GetMapping
    public List<Ingredient> getAll() { return service.findAll(); }

    //search by id
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //search by name 
    @GetMapping("/search")
    public ResponseEntity<List<Ingredient>> searchByName(@RequestParam("name") String name) {
        List<Ingredient> results = service.findByNameContaining(name);
        return ResponseEntity.ok(results); 
    }

    //create new ingredient
    @PostMapping
    public ResponseEntity<Ingredient> create(@Valid @RequestBody Ingredient ingredient) {
        Ingredient saved = service.save(ingredient);
        return ResponseEntity.created(URI.create("/api/ingredients/" + saved.getId())).body(saved);
    }

    //delete ingredient by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.findById(id); // Check if exists
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
