package com.tugas7.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tugas7.demo.models.Kelas;
import com.tugas7.demo.services.KelasService;
import com.tugas7.demo.services.PesertaService;

@Controller
public class KelasController {

    private final KelasService kelasService;
    private final PesertaService pesertaService;

    public KelasController(KelasService kelasService, PesertaService pesertaService) {
        this.kelasService = kelasService;
        this.pesertaService = pesertaService;
    }

    @GetMapping("/class")
    public String listKelas(Model model) {
        model.addAttribute("listKelas", kelasService.getAll());
        model.addAttribute("pesertaService", pesertaService);
        return "class-list";
    }

    @GetMapping("/class/add")
    public String addForm(Model model) {
        model.addAttribute("kelas", new Kelas());
        return "class-form";
    }

    @PostMapping("/class/save")
    public String save(Kelas kelas, RedirectAttributes redirectAttributes) {
        String error = kelasService.validate(kelas, true);
        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/class/add";
        }
        kelasService.save(kelas);
        redirectAttributes.addFlashAttribute("success", "Kelas berhasil ditambahkan");
        return "redirect:/class";
    }

    @GetMapping("/class/detail/{kode}")
    public String detail(@PathVariable String kode, Model model, RedirectAttributes redirectAttributes) {
        Kelas kelas = kelasService.getByKode(kode);
        if (kelas == null) {
            redirectAttributes.addFlashAttribute("error", "Kelas tidak ditemukan");
            return "redirect:/class";
        }
        model.addAttribute("kelas", kelas);
        model.addAttribute("pesertaList", pesertaService.getPesertaByKelas(kode));
        model.addAttribute("jumlahPeserta", pesertaService.getRegisteredCount(kode));
        return "class-detail";
    }

    @PostMapping("/class/delete/{kode}")
    public String delete(@PathVariable String kode, RedirectAttributes redirectAttributes) {
        kelasService.delete(kode);
        pesertaService.deleteAllByKelas(kode);
        redirectAttributes.addFlashAttribute("success", "Kelas berhasil dihapus");
        return "redirect:/class";
    }
}
