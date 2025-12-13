package com.example.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.models.Chef;
import com.example.restaurant.models.Drink;
import com.example.restaurant.models.Food;
import com.example.restaurant.repository.ChefRepository;
import com.example.restaurant.repository.DrinkRepository;
import com.example.restaurant.repository.FoodRepository;

@Service
public class ChefService {
    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    public void save(Chef chef) {
        chefRepository.save(chef);
    }

    public List<Chef> getAllChefs() { 
        return chefRepository.findAll();
    }
    
    public Chef findById(Integer id) {
        return chefRepository.findById(id).orElse(null);
    }
    
    public void deleteById(Integer id) {
        chefRepository.deleteById(id);
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Drink> getAllDrink() {
        return drinkRepository.findAll();
    }

    public List<Chef> findByNama(String name){
        return chefRepository.findAllByNameContainingIgnoreCase(name);
    }
}