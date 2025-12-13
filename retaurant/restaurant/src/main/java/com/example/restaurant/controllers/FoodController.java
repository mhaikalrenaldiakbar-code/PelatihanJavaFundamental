package com.example.restaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restaurant.models.Food;
import com.example.restaurant.service.FoodService;

@Controller
public class FoodController {
    @Autowired
    private FoodService foodService;

    // --- READ (List, Search, Sort) ---

    @GetMapping("/list-food")
    public String list(Model model) {
        List<Food> food = foodService.getAllFood();
        model.addAttribute("food", food);
        return "list-food";
    }

    @GetMapping("search")
    public String searchFoodByName(@RequestParam(value = "name") String name, Model model) {
        List<Food> food = foodService.findByName(name);
        model.addAttribute("food", food);
        return "list-food";
    }

    @GetMapping("/sort-by-price-desc")
    public String sortByPriceDesc(Model model) {
        List<Food> food = foodService.findAllByOrderByPriceDesc();
        model.addAttribute("food", food);
        return "list-food";
    }

    @GetMapping("/sort-by-price-asc")
    public String sortByPriceAsc(Model model) {
        List<Food> food = foodService.findAllByOrderByPriceAsc();
        model.addAttribute("food", food);
        return "list-food";
    }
    
    // --- CREATE ---
    
    @GetMapping("/add-food")
    public String addFood(Model model) {
        Food food = new Food();
        model.addAttribute("food", food);
        return "add-food";
    }

    @PostMapping("save-food")
    public String saveFood(@ModelAttribute("food") Food food) {
        foodService.save(food);
        return "redirect:/list-food";
    }

    // --- DELETE ---

    @GetMapping("delete-food/{id}")
    public String deleteFood(@PathVariable(value = "id") Integer id) {
        foodService.deleteById(id);
        return "redirect:/list-food";
    }
    
    // --- UPDATE (PENTING: Tambahkan ini) ---

    // 1. Menampilkan Formulir Edit (GET request)
    @GetMapping("/update-food/{id}")
    public String showUpdateFormFood(@PathVariable("id") Integer id, Model model) {
        Food food = foodService.findById(id);
        model.addAttribute("food", food);
        // Membutuhkan template update-food.html
        return "update-food"; 
    }

    // 2. Menyimpan Hasil Edit (POST request)
    @PostMapping("/update-food/{id}")
    public String updateFood(@PathVariable(value = "id") Integer id, @ModelAttribute("food") Food food) {
        Food existingFood = foodService.findById(id);
        if (existingFood != null) {
            // Pastikan Food model memiliki getName() dan getPrice() (Lombok)
            existingFood.setName(food.getName());
            existingFood.setPrice(food.getPrice());
            foodService.save(existingFood);
        }
        return "redirect:/list-food";
    }
}