package com.chefathands.ingredient.service;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.repository.UserIngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserIngredientService {
    
    private final UserIngredientRepository repository;
    
    public UserIngredientService(UserIngredientRepository repository) {
        this.repository = repository;
    }
    
    public List<UserIngredient> getUserIngredients(Integer userId) {
        return repository.findByUserId(userId);
    }
    
    @Transactional
    public UserIngredient addUserIngredient(UserIngredient userIngredient) {
        return repository.save(userIngredient);
    }
    
    @Transactional
    public void removeUserIngredient(Integer userId, Integer userIngredientId) {
        UserIngredient userIngredient = repository.findById(userIngredientId)
            .orElseThrow(() -> new RuntimeException("User ingredient not found"));
        
        if (!userIngredient.getUserId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }
        
        repository.delete(userIngredient);
    }
    
    @Transactional
    public UserIngredient updateQuantity(Integer userId, Integer userIngredientId, String quantity) {
        UserIngredient userIngredient = repository.findById(userIngredientId)
            .orElseThrow(() -> new RuntimeException("User ingredient not found"));
        
        if (!userIngredient.getUserId().equals(userId)) {
            throw new RuntimeException("Not authorized to update this ingredient");
        }
        
        userIngredient.setQuantity(quantity);
        
        return repository.save(userIngredient);
    }
}