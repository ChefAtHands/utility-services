package com.chefathands.ingredient.repository;

import com.chefathands.ingredient.model.UserIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Integer> {
    List<UserIngredient> findByUserId(Integer userId);
    Optional<UserIngredient> findByUserIdAndIngredientId(Integer userId, Integer ingredientId);
    void deleteByUserIdAndIngredientId(Integer userId, Integer ingredientId);
}