package com.tugas7.demo.controller;

import com.tugas7.demo.model.Participant;
import com.tugas7.demo.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/register/{classCode}")
    public String registerForm(@PathVariable String classCode, Model model) {
        model.addAttribute("participant", new Participant());
        model.addAttribute("classCode", classCode);
        return "register-participant";
    }

    @PostMapping("/save")
    public String saveRegistration(@RequestParam String classCode, @ModelAttribute Participant participant, RedirectAttributes redirectAttributes) {
        String error = participantService.registerParticipant(classCode, participant);
        if (error != null) {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:/participants/register/" + classCode;
        }
        redirectAttributes.addFlashAttribute("success", "Registration successful.");
        return "redirect:/class/detail/" + classCode;
    }

    @PostMapping("/delete/{classCode}/{email}")
    public String cancelRegistration(@PathVariable String classCode, @PathVariable String email, RedirectAttributes redirectAttributes) {
        participantService.cancelRegistration(classCode, email);
        redirectAttributes.addFlashAttribute("success", "Registration cancelled.");
        return "redirect:/class/detail/" + classCode;
    }
}