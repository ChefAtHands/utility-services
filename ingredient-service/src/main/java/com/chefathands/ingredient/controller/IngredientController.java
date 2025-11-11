package com.chefathands.ingredient.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.chefathands.ingredient.service.IngredientService;
import com.chefathands.ingredient.model.Ingredient;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    
    private final IngredientService service;
    
    public IngredientController(IngredientService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Ingredient> getAll() {
        return service.findAll();
    }
    
    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return service.save(ingredient);
    }
}
