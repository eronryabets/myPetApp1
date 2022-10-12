package com.eronryabets.first_pet.controller;


import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.exceptions.UserExist;
import com.eronryabets.first_pet.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(Model model, User user) {

        try {
            userService.addUser(user);
        } catch (UserExist e){
            model.addAttribute("errorMessage", "This username " + user.getUsername() +
                    " already exist! Please input another username");
            model.addAttribute("username", user.getUsername())
                    .addAttribute("surname",user.getSurname())
                    .addAttribute("name",user.getName());
            return "registration";
        }

        return "redirect:/login";
    }

}