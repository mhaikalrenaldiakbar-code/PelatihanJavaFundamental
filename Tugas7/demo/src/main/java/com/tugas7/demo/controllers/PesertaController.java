package com.tugas7.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tugas7.demo.models.Kelas;
import com.tugas7.demo.models.Peserta;
import com.tugas7.demo.services.KelasService;
import com.tugas7.demo.services.PesertaService;

@Controller
public class PesertaController {

    private final KelasService kelasService;
    private final PesertaService pesertaService;

    public PesertaController(KelasService kelasService, PesertaService pesertaService) {
        this.kelasService = kelasService;
        this.pesertaService = pesertaService;
    }

    @GetMapping("/peserta/register/{kodeKelas}")
    public String registerForm(@PathVariable String kodeKelas, Model model, RedirectAttributes redirectAttributes) {
        Kelas kelas = kelasService.getByKode(kodeKelas);
        if (kelas == null) {
            redirectAttributes.addFlashAttribute("error", "Kelas tidak ditemukan");
            return "redirect:/class";
        }
        model.addAttribute("kelas", kelas);
        model.addAttribute("peserta", new Peserta());
        return "peserta-form";
    }

    @PostMapping("/peserta/save")
    public String save(@RequestParam String kodeKelas, Peserta peserta, RedirectAttributes redirectAttributes) {
        Kelas kelas = kelasService.getByKode(kodeKelas);
        if (kelas == null) {
            redirectAttributes.addFlashAttribute("error", "Kelas tidak ditemukan");
            return "redirect:/class";
        }

        String error = pesertaService.validate(peserta, kodeKelas, kelas.getKapasitasMaksimal());
        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/peserta/register/" + kodeKelas;
        }

        pesertaService.register(kodeKelas, peserta);
        redirectAttributes.addFlashAttribute("success", "Pendaftaran berhasil");
        return "redirect:/class/detail/" + kodeKelas;
    }

    @PostMapping("/peserta/delete/{kodeKelas}/{email}")
    public String cancelRegistration(@PathVariable String kodeKelas, @PathVariable String email, 
                                     RedirectAttributes redirectAttributes) {
        pesertaService.cancelRegistration(kodeKelas, email);
        redirectAttributes.addFlashAttribute("success", "Pendaftaran berhasil dibatalkan");
        return "redirect:/class/detail/" + kodeKelas;
    }
}
