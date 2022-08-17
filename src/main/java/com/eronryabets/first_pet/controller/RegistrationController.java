package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

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
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("password") String password
                          ){
        User user = new User(username,name,surname,password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/";
    }

}

