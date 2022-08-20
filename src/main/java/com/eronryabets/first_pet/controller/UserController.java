package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("password") String password,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        userService.userSave(user,username,name,surname,password,form);
        return "redirect:/user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{user}/delete",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String userDelete(@PathVariable User user){
        userService.userDelete(user);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model,
                             @AuthenticationPrincipal User user){
        model.addAttribute("user",user);
        return "profile";
    }

    @GetMapping("profile/profileData")
    public String getProfileData(Model model,
                             @AuthenticationPrincipal User user){
        model.addAttribute("user",user);
        return "profileData";
    }

    @PostMapping("profile/profileData")
    public String updateProfileData(
            @AuthenticationPrincipal User user,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("password") String password
    ){
        userService.profileSave(user, name, surname, password);
        return "redirect:/user/profile";
    }




}

