package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{userID}")
    public String userEditForm(@PathVariable User userID, Model model
                               //@RequestParam("user") User user
    ){
        model.addAttribute("user",userID);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{userID}", method = RequestMethod.POST)
    public String userSave(
            @PathVariable("userID")User userID,
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("password") String password,
            @RequestParam Map<String, String> form,
            @RequestParam("userID") User user
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
            @RequestParam("password") String password,
            @RequestParam("avatar") MultipartFile avatar
    ) throws IOException {
        userService.profileSave(user, name, surname, password, avatar);
        return "redirect:/user/profile";
    }




}

