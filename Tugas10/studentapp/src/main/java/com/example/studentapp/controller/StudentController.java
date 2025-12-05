package com.example.studentapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream; // Digunakan untuk membuat daftar nomor halaman

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // Diperlukan untuk Pagination
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Rute: /students (GET) -> List, Pagination, Search, Filter
    @GetMapping
    public String viewHomePage(
            // 🔥 Parameter Pagination (Default)
            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            // Parameter Search & Filter
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "searchId", required = false) Long searchId,
            @RequestParam(value = "filterMajor", required = false) String filterMajor, // 🔥 Filter Jurusan
            Model model) {

        List<Student> students;

        // 1. Handle Search By ID (Prioritas Tertinggi)
        if (searchId != null) {
            try {
                Student foundStudent = studentService.getStudentById(searchId);
                students = List.of(foundStudent);
                model.addAttribute("searchId", searchId);
                model.addAttribute("listStudents", students);
                return "students"; // Tampilkan hasil Search ID
            } catch (RuntimeException e) {
                // Jika ID tidak ditemukan, tampilkan pesan error dan kembali ke tampilan default
                model.addAttribute("error", "Mahasiswa dengan ID " + searchId + " tidak ditemukan.");
                model.addAttribute("searchId", searchId);
                // Lanjutkan ke pagination default
            }
        }

        // 2. Handle Filter By Major (Pencarian fleksibel)
        if (filterMajor != null && !filterMajor.isEmpty()) {
            students = studentService.filterByMajor(filterMajor);
            model.addAttribute("listStudents", students);
            model.addAttribute("filterMajor", filterMajor); // Pertahankan nilai di input
            return "students"; // Tampilkan hasil Filter Jurusan
        }

        // 3. Handle Search By Name
        if (keyword != null && !keyword.isEmpty()) {
            students = studentService.searchStudentsByName(keyword);
            model.addAttribute("listStudents", students);
            model.addAttribute("keyword", keyword); // Pertahankan nilai di input
            return "students"; // Tampilkan hasil Search Nama
        }

        // 4. Logika Default: Pagination + Sorting
        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        students = page.getContent();

        // Tambahkan atribut Pagination ke Model
        model.addAttribute("listStudents", students);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        // Logika untuk menampilkan tombol nomor halaman (misal: 1, 2, 3...)
        if (page.getTotalPages() > 0) {
            int maxPage = Math.min(page.getTotalPages(), 5);
            List<Integer> pageNumbers = IntStream.rangeClosed(1, maxPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "students";
    }

    // Rute: /students/add (GET) -> Form Tambah
    @GetMapping("/add")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Rute: /students/add (POST) -> Create
    @PostMapping("/add")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "add-student";
        }

        if (!studentService.isEmailUnique(student.getEmail(), student.getId())) {
            bindingResult.rejectValue("email", "error.student", "Email ini sudah terdaftar.");
            return "add-student";
        }

        try {
            studentService.saveStudent(student);
            // 🔥 Notifikasi Sukses
            redirectAttributes.addFlashAttribute("message", "Data mahasiswa berhasil ditambahkan!");
        } catch (Exception e) {
            // 🔥 Notifikasi Gagal
            redirectAttributes.addFlashAttribute("error", "Gagal menyimpan data: " + e.getMessage());
        }

        return "redirect:/students";
    }

    // Rute: /students/edit/{id} (GET) -> Form Edit (Tampilkan Berdasarkan ID)
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    // Rute: /students/edit (POST) -> Update
    @PostMapping("/edit")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "edit-student";
        }

        if (!studentService.isEmailUnique(student.getEmail(), student.getId())) {
            bindingResult.rejectValue("email", "error.student", "Email ini sudah terdaftar.");
            return "edit-student";
        }

        try {
            studentService.saveStudent(student);
            // 🔥 Notifikasi Sukses
            redirectAttributes.addFlashAttribute("message", "Data mahasiswa ID " + student.getId() + " berhasil diupdate!");
        } catch (Exception e) {
            // 🔥 Notifikasi Gagal
            redirectAttributes.addFlashAttribute("error", "Gagal mengupdate data: " + e.getMessage());
        }

        return "redirect:/students";
    }

    // Rute: /students/delete/{id} -> Delete By ID
    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            this.studentService.deleteStudentById(id);
            // 🔥 Notifikasi Sukses
            redirectAttributes.addFlashAttribute("message", "Data mahasiswa ID " + id + " berhasil dihapus!");
        } catch (Exception e) {
            // 🔥 Notifikasi Gagal
            redirectAttributes.addFlashAttribute("error", "Gagal menghapus data ID " + id + ".");
        }
        return "redirect:/students";
    }

    // Rute: /students/deleteByName (POST) -> Delete By Name
    @PostMapping("/deleteByName")
    public String deleteStudentByName(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        if (name == null || name.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nama tidak boleh kosong untuk penghapusan.");
            return "redirect:/students";
        }

        try {
            studentService.deleteStudentByName(name);
            // 🔥 Notifikasi Sukses
            redirectAttributes.addFlashAttribute("message", "Semua data mahasiswa dengan nama '" + name + "' berhasil dihapus!");
        } catch (Exception e) {
            // 🔥 Notifikasi Gagal
            redirectAttributes.addFlashAttribute("error", "Gagal menghapus data berdasarkan nama.");
        }
        return "redirect:/students";
    }
}
