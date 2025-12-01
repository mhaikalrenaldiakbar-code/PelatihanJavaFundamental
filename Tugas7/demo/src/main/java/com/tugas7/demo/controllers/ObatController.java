package com.tugas7.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tugas7.demo.models.Obat;
import com.tugas7.demo.services.ObatService;

@Controller
public class ObatController {

    private final ObatService obatService;

    public ObatController(ObatService obatService) {
        this.obatService = obatService;
    }

    @GetMapping("/obat/list")
    public String listObat(Model model) {
    model.addAttribute("listObat", obatService.getAll());
    return "list-obat";
}

    @GetMapping("/obat/add")
    public String addForm(Model model) {
        model.addAttribute("obat", new Obat());
        return "form-obat";
    }

    @PostMapping("/obat/save")
    public String save(Obat obat) {
        obatService.save(obat);
        return "redirect:/obat/list";
    }

    @GetMapping("/obat/edit/{kode}")
    public String edit(@PathVariable String kode, Model model) {
        model.addAttribute("obat", obatService.getByKode(kode));
        return "form-obat";
    }

    @GetMapping("/obat/delete/{kode}")
    public String delete(@PathVariable String kode) {
        obatService.delete(kode);
        return "redirect:/obat/list";
    }
}
