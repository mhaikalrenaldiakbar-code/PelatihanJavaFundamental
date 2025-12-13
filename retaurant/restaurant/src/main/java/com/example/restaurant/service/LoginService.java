package com.example.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.models.Login;
import com.example.restaurant.repository.LoginRepository;

@Service
public class LoginService {
    @Autowired 
    private LoginRepository loginRepository;

    public void save(Login login){
        loginRepository.save(login);
    }

    public Login findByUsernameAndPassword(String username, String password){
        List <Login> login = loginRepository.findByUsernameAndPassword(username, password);
        return login.stream()
                     .findFirst()
                     .orElse(null);
    }

    public void deleteById(Integer id){
        loginRepository.deleteById(id);
    }

   public Login findById(Integer id){
     return loginRepository.findById(id).orElse(null);
   }

    public List<Login> getAllLogin(){
        return loginRepository.findAll();
    }
}