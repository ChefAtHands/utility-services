package com.chefathands.ingredient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.chefathands.ingredient",
    "com.chefathands.logging"})
public class IngredientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IngredientServiceApplication.class, args);
    }
}