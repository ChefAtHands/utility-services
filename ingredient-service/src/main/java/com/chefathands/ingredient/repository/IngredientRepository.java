package com.chefathands.ingredient.repository;

import com.chefathands.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameContainingIgnoreCase(String name);
}