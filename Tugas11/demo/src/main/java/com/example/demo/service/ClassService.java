package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;

@Service
@Transactional 
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> findAllClasses() {
        return classRepository.findAll();
    }

    public Class findClassById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid class Id:" + id));
    }

    public Class saveClass(Class aClass) {
        return classRepository.save(aClass);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
}