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

import com.example.restaurant.models.Login;
import com.example.restaurant.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("registrasi")
    public String registrasi(Model model) {
        Login login = new Login();
        model.addAttribute("regis", login);
        return "registrasi";
    }

    @PostMapping("save-regis")
    public String saveRegis(@ModelAttribute("regis") Login login) {
        loginService.save(login);
        return "redirect:login"; 
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("cek-login")
    public String login(@RequestParam("username") String username,
            @RequestParam("password") String password, Model model) {
        Login login = loginService.findByUsernameAndPassword(username, password);
        if (login != null) {
            model.addAttribute("user", login); 
            return "home";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    @GetMapping("delete-akun/{id}")
    public String deleteAkun(@PathVariable(value = "id") Integer id) {
        loginService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("home")
    public String home(Model model) {
        List<Login> login = loginService.getAllLogin();
        model.addAttribute("login", login);
        return ("home");
    }

    @GetMapping("/update-akun/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Login login = loginService.findById(id);
        model.addAttribute("login", login);
        return "update-akun";
    }

    @PostMapping("/update-akun/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute("login") Login login) {
        Login update = loginService.findById(id);
        if (update != null) {
            update.setPassword(login.getPassword());
            update.setUsername(login.getUsername());
            loginService.save(update); 
        }
        return "redirect:/home";
    }
}