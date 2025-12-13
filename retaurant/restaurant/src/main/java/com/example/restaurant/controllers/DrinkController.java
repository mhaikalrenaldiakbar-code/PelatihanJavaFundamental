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

import com.example.restaurant.models.Drink;
// import com.example.restaurant.models.Food; // Food tidak diperlukan di sini
import com.example.restaurant.service.DrinkService;

@Controller
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    // --- READ (List) ---
    @GetMapping("/list-drink")
    public String list(Model model) {
        List<Drink> drink = drinkService.getAllDrink();
        model.addAttribute("drink", drink);
        return "list-drink";
    }

    // --- CREATE ---
    @GetMapping("/add-drink")
    public String addDrink(Model model) {
        Drink drink = new Drink();
        model.addAttribute("drink", drink);
        return "add-drink";
    }

    @PostMapping("save-drink")
    public String saveDrink(@ModelAttribute("drink") Drink drink) {
        drinkService.save(drink);
        return "redirect:/list-drink";
    }

    // --- DELETE ---
    @GetMapping("delete-drink/{id}")
    public String deleteDrink(@PathVariable(value = "id") Integer id) {
        drinkService.deleteById(id);
        return "redirect:/list-drink";
    }

    // --- UPDATE ---
    @GetMapping("/update-drink/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Drink drink = drinkService.findById(id);
        model.addAttribute("drink", drink);
        return "update-drink";
    }

    @PostMapping("/update-drink/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute("drink") Drink drink) {
        Drink existingDrink = drinkService.findById(id);
        if (existingDrink != null) {
            existingDrink.setName(drink.getName());
            existingDrink.setPrice(drink.getPrice());
            drinkService.save(existingDrink);
        }
        return "redirect:/list-drink";
    }

    // --- SEARCH (Fungsi Baru) ---
    @GetMapping("/search-drink")
    public String searchDrinkByName(@RequestParam(value = "name") String name, Model model) {
        List<Drink> drink = drinkService.findByName(name);
        model.addAttribute("drink", drink);
        return "list-drink";
    }

    // --- SORT (Fungsi Baru) ---
    @GetMapping("/sort-drink-by-price-desc")
    public String sortByPriceDesc(Model model) {
        List<Drink> drink = drinkService.findAllByOrderByPriceDesc();
        model.addAttribute("drink", drink);
        return "list-drink";
    }

    @GetMapping("/sort-drink-by-price-asc")
    public String sortByPriceAsc(Model model) {
        List<Drink> drink = drinkService.findAllByOrderByPriceAsc();
        model.addAttribute("drink", drink);
        return "list-drink";
    }
}