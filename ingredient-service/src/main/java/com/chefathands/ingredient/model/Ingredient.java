package com.chefathands.ingredient.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
 


@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "name must not be blank")
    @Column(unique = true, nullable = false)
    private String name;
    
    private String category;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
