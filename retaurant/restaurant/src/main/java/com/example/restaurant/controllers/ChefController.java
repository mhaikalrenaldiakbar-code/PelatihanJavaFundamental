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

import com.example.restaurant.models.Chef;
import com.example.restaurant.models.Drink;
import com.example.restaurant.models.Food;
import com.example.restaurant.service.ChefService;

@Controller
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/list-chef")
    public String listChefs(Model model) {
        List<Chef> chefs = chefService.getAllChefs();
        model.addAttribute("chefs", chefs);
        return "list-chef";
    }

    @GetMapping("/add-chef")
    public String addChef(Model model) {
        Chef chef = new Chef();
        List<Food> foodList = chefService.getAllFood();
        List<Drink> drinkList = chefService.getAllDrink();

        model.addAttribute("chef", chef);
        model.addAttribute("foodList", foodList);
        model.addAttribute("drinkList", drinkList);
        return "add-chef";
    }

    @PostMapping("/save-chef")
    public String saveChef(@ModelAttribute("chef") Chef chef) {
        chefService.save(chef);
        return "redirect:/list-chef";
    }

    @GetMapping("delete-chef/{id}")
    public String deleteChef(@PathVariable(value = "id") Integer id) {
        chefService.deleteById(id);
        return "redirect:/list-chef";
    }

    @GetMapping("/search-chef")
    public String searchChefByName(@RequestParam(value = "name") String name, Model model) {
        List<Chef> chefs = chefService.findByNama(name);
        model.addAttribute("chefs", chefs);
        return "list-chef";
    }

// 1. Menampilkan Formulir Edit Chef (GET request)
    @GetMapping("/update-chef/{id}")
    public String showUpdateFormChef(@PathVariable("id") Integer id, Model model) {
        // 1. Ambil data Chef yang akan diedit
        Chef chef = chefService.findById(id);

        // 2. Ambil daftar Food dan Drink untuk opsi dropdown
        List<Food> foodList = chefService.getAllFood();
        List<Drink> drinkList = chefService.getAllDrink();

        model.addAttribute("chef", chef);
        model.addAttribute("foodList", foodList);
        model.addAttribute("drinkList", drinkList);

        return "update-chef"; // Akan mencari template update-chef.html
    }

// 2. Menyimpan Hasil Edit Chef (POST request)
    @PostMapping("/update-chef/{id}")
    public String updateChef(@PathVariable(value = "id") Integer id, @ModelAttribute("chef") Chef chef) {
        Chef existingChef = chefService.findById(id);
        if (existingChef != null) {
            // Hanya update field yang bisa diubah
            existingChef.setName(chef.getName());
            existingChef.setAsal(chef.getAsal());
            existingChef.setFood(chef.getFood());
            existingChef.setDrink(chef.getDrink());
            chefService.save(existingChef);
        }
        return "redirect:/list-chef";
    }
}
