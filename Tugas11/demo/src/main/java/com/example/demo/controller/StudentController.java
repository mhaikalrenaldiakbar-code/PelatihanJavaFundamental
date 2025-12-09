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

import com.example.demo.model.Student;
import com.example.demo.service.ClassService;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService; // Digunakan untuk mendapatkan daftar kelas (dropdown)

    @GetMapping // /students
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "students"; // students.html
    }

    @GetMapping("/add") // /students/add (GET)
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("classes", classService.findAllClasses()); // Ambil daftar kelas dari Service
        return "add-student"; // add-student.html
    }

    @PostMapping("/add") // /students/add (POST)
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("classes", classService.findAllClasses()); // Reload data kelas saat error
            return "add-student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}") // /students/edit/{id} (GET)
    public String editStudentForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.findStudentById(id));
        model.addAttribute("classes", classService.findAllClasses()); // Ambil daftar kelas dari Service
        return "edit-student"; // edit-student.html
    }

    @PostMapping("/edit") // /students/edit (POST)
    public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("classes", classService.findAllClasses()); // Reload data kelas saat error
            return "edit-student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}") // /students/delete/{id}
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
