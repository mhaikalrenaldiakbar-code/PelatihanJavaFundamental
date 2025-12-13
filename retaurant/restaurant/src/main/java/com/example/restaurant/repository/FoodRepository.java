package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.models.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findAllByNameContainingIgnoreCase(String name);
    List<Food> findAllByOrderByPriceDesc();
    List<Food> findAllByOrderByPriceAsc();
}