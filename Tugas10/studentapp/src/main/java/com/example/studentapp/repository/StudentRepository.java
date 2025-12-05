package com.example.studentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentapp.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findByNameContainingIgnoreCase(String keyword);

    void deleteByName(String name);

    List<Student> findByMajorContainingIgnoreCase(String majorKeyword);
}
