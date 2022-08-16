package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("{user}/editProfile")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "profileEdit";
    }
}
