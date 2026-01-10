package com.chefathands.ingredient.service;

import com.chefathands.ingredient.model.UserIngredient;
import com.chefathands.ingredient.repository.UserIngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserIngredientService {
    private static final Logger logger = LoggerFactory.getLogger(UserIngredientService.class);
    
    private final UserIngredientRepository repository;
    
    public UserIngredientService(UserIngredientRepository repository) {
        this.repository = repository;
    }
    
    public List<UserIngredient> getUserIngredients(Integer userId) {
        return repository.findByUserId(userId);
    }
    
    @Transactional
    public UserIngredient addUserIngredient(UserIngredient userIngredient) {
        UserIngredient saved = repository.save(userIngredient);
        logger.info("UserIngredient added for UserId={}, ingredientId={}", saved.getUserId(), saved.getIngredientId());
        return saved;
    }
    
    @Transactional
    public void removeUserIngredient(Integer userId, Integer userIngredientId) {
        UserIngredient userIngredient = repository.findById(userIngredientId)
            .orElseThrow(() -> {
                logger.error("Remove failed: UserIngredient not found id={}", userIngredientId);
                return new RuntimeException("User ingredient not found");
            });
        
        if (!userIngredient.getUserId().equals(userId)) {
            logger.error("Unauthorized remove attempt: userId={} tried to remove ingredientId={}", userId, userIngredientId);
            throw new RuntimeException("Not authorized");
        }
        
        repository.delete(userIngredient);
        logger.warn("userIngredient removed: id={} by userId={}", userIngredientId, userId);
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