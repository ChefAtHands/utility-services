package com.chefathands.ingredient.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chefathands.ingredient.exception.ResourceNotFoundException;
import com.chefathands.ingredient.model.Ingredient;
import com.chefathands.ingredient.repository.IngredientRepository;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {
    private static final Logger logger = LoggerFactory.getLogger(IngredientService.class);
    
    private final IngredientRepository repo;
    
    public IngredientService(IngredientRepository repo) { 
        this.repo = repo;
    }

    public Ingredient save(Ingredient i) { 
        Ingredient saved = repo.save(i);
        logger.info("ingredient saved: {} (id={})", saved.getName(), saved.getId());
        return saved; 
    }
    
    public Ingredient findById(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> {
                logger.error("Ingredient not found with id {}", id);
                return new ResourceNotFoundException("Ingredient not found with id " + id);
            });
    }

    public List<Ingredient> findAll() {
        return repo.findAll();
    }
    
    public List<Ingredient> findByNameContaining(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
    
    @Transactional
    public void deleteById(Integer id) {
        repo.deleteById(id);
        logger.warn("ingredient deleted with id {}", id);
    }
}