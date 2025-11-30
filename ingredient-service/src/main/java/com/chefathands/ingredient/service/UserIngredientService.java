package com.chefathands.ingredient.service;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.repository.UserIngredientRepository;
import com.chefathands.ingredient.exception.ResourceNotFoundException;
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
    
    public UserIngredient addUserIngredient(UserIngredient userIngredient) {
        return repository.save(userIngredient);
    }
    
    @Transactional
    public void removeUserIngredient(Integer userId, Integer ingredientId) {
        repository.deleteByUserIdAndIngredientId(userId, ingredientId);
    }
    
    public UserIngredient updateQuantity(Integer userId, Integer ingredientId, String quantity) {
        UserIngredient userIngredient = repository.findByUserIdAndIngredientId(userId, ingredientId)
            .orElseThrow(() -> new ResourceNotFoundException("User ingredient not found"));
        userIngredient.setQuantity(quantity);
        return repository.save(userIngredient);
    }
}
