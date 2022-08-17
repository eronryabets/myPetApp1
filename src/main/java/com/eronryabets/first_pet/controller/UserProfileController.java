package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("{user}")
    public String userProfile(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        //System.out.println(user.getId());
        return "profile";
    }

    @GetMapping("{user}/profileEdit")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "profileEdit";
    }

    @PostMapping("{user}/profileEdit")
    public String userSave(@PathVariable("user") User user,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("password") String password
    ){
        System.out.println(user);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/profile/{user}/profileEdit";
    }



}
