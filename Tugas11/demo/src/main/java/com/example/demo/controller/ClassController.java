package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Class;
import com.example.demo.service.ClassService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService; // Injeksi ClassService

    @GetMapping // /classes
    public String listClasses(Model model) {
        model.addAttribute("classes", classService.findAllClasses());
        return "classes"; // classes.html
    }

    @GetMapping("/add") // /classes/add (GET)
    public String addClassForm(Model model) {
        model.addAttribute("aClass", new Class());
        return "add-class"; // add-class.html
    }

    @PostMapping("/add") // /classes/add (POST)
    public String saveClass(@Valid @ModelAttribute("aClass") Class aClass, BindingResult result) {
        if (result.hasErrors()) {
            return "add-class";
        }
        classService.saveClass(aClass);
        return "redirect:/classes";
    }

    @GetMapping("/edit/{id}") // /classes/edit/{id} (GET)
    public String editClassForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("aClass", classService.findClassById(id));
        return "edit-class"; // edit-class.html
    }

    @PostMapping("/edit") // /classes/edit (POST)
    public String updateClass(@Valid @ModelAttribute("aClass") Class aClass, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-class";
        }
        classService.saveClass(aClass);
        return "redirect:/classes";
    }

    @GetMapping("/delete/{id}") // /classes/delete/{id}
    public String deleteClass(@PathVariable("id") Long id) {
        classService.deleteClass(id);
        return "redirect:/classes";
    }

    @GetMapping("/{id}") // /classes/{id} (Detail Kelas + Mahasiswa)
    public String classDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("aClass", classService.findClassById(id));
        return "class-detail"; // class-detail.html
    }
}
