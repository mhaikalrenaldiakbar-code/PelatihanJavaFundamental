package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.models.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    List<Login> findByUsernameAndPassword(String username, String password);
}
