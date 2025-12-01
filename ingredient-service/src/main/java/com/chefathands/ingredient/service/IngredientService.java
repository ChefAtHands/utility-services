package com.chefathands.ingredient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chefathands.ingredient.model.Ingredient;
import com.chefathands.ingredient.repository.IngredientRepository;
import com.chefathands.ingredient.exception.ResourceNotFoundException;

@Service
public class IngredientService {
    private final IngredientRepository repo;
    
    public IngredientService(IngredientRepository repo) { 
        this.repo = repo; 
    }

    public Ingredient save(Ingredient i) { 
        return repo.save(i); 
    }
    
      public Ingredient findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + id));
    }

    public List<Ingredient> findAll() {
        return repo.findAll();
    }
    
    public List<Ingredient> findByNameContaining(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

}