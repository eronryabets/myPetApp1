package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("password") String password
                          ){
        User user = new User(login,name,surname,password);
        userRepository.save(user);
        return "redirect:/";
    }

}

/*
@PostMapping("/registration")
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("password") String password,
                          Model model){
        User user = new User(login,name,surname,password);
        userRepository.save(user);
        return "redirect:/";
    }
 */