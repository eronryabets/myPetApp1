package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        System.out.println(user);
        model.addAttribute("user",user);
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam("login") String login,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("password") String password,
            @RequestParam("userId") User user
    ){
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/user";
    }


}
