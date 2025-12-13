package com.example.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.models.Drink;
import com.example.restaurant.repository.DrinkRepository; // Pastikan ini diimpor

@Service // Menandakan bahwa ini adalah Service
public class DrinkService { // Ini adalah Class, bukan Interface

    @Autowired
    private DrinkRepository drinkRepository; // Autowire Repositori

    // --- CRUD Utama ---
    public List<Drink> getAllDrink() {
        return drinkRepository.findAll();
    }

    public void save(Drink drink) {
        drinkRepository.save(drink);
    }

    public Drink findById(Integer id) {
        Optional<Drink> optional = drinkRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        // Menangani jika Drink tidak ditemukan
        throw new RuntimeException("Minuman tidak ditemukan untuk id :: " + id);
    }

    public void deleteById(Integer id) {
        drinkRepository.deleteById(id);
    }

    // --- Search dan Sort (Fungsi Baru) ---
    // Metode yang dipanggil oleh DrinkController
    public List<Drink> findByName(String name) {
        // Memanggil metode yang ada di DrinkRepository
        return drinkRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Drink> findAllByOrderByPriceDesc() {
        return drinkRepository.findAllByOrderByPriceDesc();
    }

    public List<Drink> findAllByOrderByPriceAsc() {
        return drinkRepository.findAllByOrderByPriceAsc();
    }
}
