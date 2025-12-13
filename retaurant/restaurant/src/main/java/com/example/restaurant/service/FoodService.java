package com.example.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.models.Food;
import com.example.restaurant.repository.FoodRepository;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public void save(Food food) {
        foodRepository.save(food);
    }

    public void deleteById(Integer id) {
        foodRepository.deleteById(id);
    }

    public Food findById(Integer id) {
        return foodRepository.findById(id).orElse(null);
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public List<Food> findByName(String name){
        return foodRepository.findAllByNameContainingIgnoreCase(name);
    }

    public List<Food> findAllByOrderByPriceDesc(){
        return foodRepository.findAllByOrderByPriceDesc();
    }

    public List<Food> findAllByOrderByPriceAsc(){
        return foodRepository.findAllByOrderByPriceAsc();
    }
}