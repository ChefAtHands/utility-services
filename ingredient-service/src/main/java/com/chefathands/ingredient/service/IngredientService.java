package com.chefathands.ingredient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chefathands.ingredient.model.Ingredient;
import com.chefathands.ingredient.repository.IngredientRepository;
import com.chefathands.ingredient.exception.ResourceNotFoundException;
import com.chefathands.logging.service.LogService;

@Service
public class IngredientService {
    private final IngredientRepository repo;
    private final LogService logService;
    
    public IngredientService(IngredientRepository repo, LogService logService) { 
        this.repo = repo; 
        this.logService = logService;
    }

    public Ingredient save(Ingredient i) { 
        Ingredient saved = repo.save(i);
        logService.logInfo("ingredient saved: " + saved.getName() + "(id=" + saved.getId()+ ")");
        return saved; 
    }
    
      public Ingredient findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> {
                logService.logError("Ingredient not found with id " + id);
                return new ResourceNotFoundException("Ingredient not found with id " + id);
            });
    }

    public List<Ingredient> findAll() {
        return repo.findAll();
    }
    
    public List<Ingredient> findByNameContaining(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
        logService.logWarn("ingredient deleted with id " + id);
    }

}