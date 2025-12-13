package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.models.Chef;

public interface ChefRepository extends JpaRepository<Chef, Integer> {
    List<Chef> findAllByNameContainingIgnoreCase(String name);
}