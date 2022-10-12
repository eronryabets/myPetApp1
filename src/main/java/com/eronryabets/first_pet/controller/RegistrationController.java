package com.eronryabets.first_pet.controller;


import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.exceptions.UserExist;
import com.eronryabets.first_pet.service.UserService;
import com.eronryabets.first_pet.utility.UtilsBindingErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;


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
    public String addUser(Model model, @Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult
    ) {

        model.addAttribute("username", user.getUsername())
                .addAttribute("surname",user.getSurname())
                .addAttribute("name",user.getName());

        if(bindingResult.hasErrors()){
            Map<String, String> errors = UtilsBindingErrors.getErrors(bindingResult);
            model.mergeAttributes(errors);
            System.out.println("BINDING ERROR");
            return "registration";
        }

        try {
            userService.addUser(user);
        } catch (UserExist e){
            model.addAttribute("errorMessage", "This username " + user.getUsername() +
                    " already exist! Please input another username");
            System.out.println("EXCEPTION USER EXIST ERROR");
            return "registration";
        }

        return "redirect:/login";
    }

}