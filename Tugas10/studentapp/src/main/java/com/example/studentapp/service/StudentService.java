package com.example.studentapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // 🔥 Import Page
import org.springframework.data.domain.PageRequest; // 🔥 Import PageRequest
import org.springframework.data.domain.Pageable; // 🔥 Import Pageable
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired

    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan untuk id: " + id));

    }

    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            student.setCreatedAt(LocalDateTime.now());
        }

        return studentRepository.save(student);

    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);

    }

    public boolean isEmailUnique(String email, Long id) {
        Optional<Student> existingStudent = studentRepository.findByEmail(email);
        if (existingStudent.isEmpty()) {
            return true;
        }

        if (id == null) {
            return false;
        }

        return existingStudent.get().getId().equals(id);

    }

    public List<Student> searchStudentsByName(String keyword) {
        return studentRepository.findByNameContainingIgnoreCase(keyword);

    }

    @Transactional

    public void deleteStudentByName(String name) {
        studentRepository.deleteByName(name);

    }

    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        // Konversi string sortDirection menjadi objek Sort.Direction
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        // Buat objek Pageable. pageNo - 1 karena Pageable berbasis 0.
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        // Panggil findAll dengan Pageable
        return studentRepository.findAll(pageable);
    }

    public List<Student> filterByMajor(String majorKeyword) {
    return studentRepository.findByMajorContainingIgnoreCase(majorKeyword);
    }
}
