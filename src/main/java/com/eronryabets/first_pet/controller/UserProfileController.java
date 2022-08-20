package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("{user}")
    public String userProfile(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "profileOLD";
    }

    @GetMapping("{user}/profileEdit")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "profileEdit";
    }

    @PostMapping("{user}/profileEdit")
    public String profileSave(@PathVariable("user") User user,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("password") String password
    ){
        userService.profileSave(user, name, surname, password);
        return "redirect:/profile/{user}/profileEdit";
    }



}
