package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.models.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {

    // Untuk Search: Menggunakan ContainingIgnoreCase untuk pencarian yang fleksibel
    List<Drink> findByNameContainingIgnoreCase(String name);

    // Untuk Sort: Diurutkan berdasarkan Harga (Price)
    List<Drink> findAllByOrderByPriceDesc();

    List<Drink> findAllByOrderByPriceAsc();
}
