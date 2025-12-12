package com.chefathands.ingredient.service;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.repository.UserIngredientRepository;
import com.chefathands.logging.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserIngredientService {
    
    private final UserIngredientRepository repository;
    private final LogService logService;
    
    public UserIngredientService(UserIngredientRepository repository, LogService logService) {
        this.repository = repository;
        this.logService = logService;
    }
    
    public List<UserIngredient> getUserIngredients(Integer userId) {
        return repository.findByUserId(userId);
    }
    
    @Transactional
    public UserIngredient addUserIngredient(UserIngredient userIngredient) {
        UserIngredient saved = repository.save(userIngredient);
        logService.logInfo("UserIngreddient added for UserId=" + saved.getUserId() + ", ingredientId=" + saved.getIngredientId());
        return saved;
    }
    
    @Transactional
    public void removeUserIngredient(Integer userId, Integer userIngredientId) {
        UserIngredient userIngredient = repository.findById(userIngredientId)
            .orElseThrow(() ->{
                logService.logError("Remove failed: UserIngredient not found id=" + userIngredientId);
                return new RuntimeException("User ingredient not found");
                });
        
        if (!userIngredient.getUserId().equals(userId)) {
            logService.logError("Unauthorized remove attempt: userId=" + userId + " tried to remove ingredientId=" + userIngredientId);
            throw new RuntimeException("Not authorized");
        }
        
        repository.delete(userIngredient);
        logService.logWarn("userIngredient removed: id=" + userIngredientId + " by userId=" + userId);
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