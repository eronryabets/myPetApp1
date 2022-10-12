package com.eronryabets.first_pet.controller;


import com.eronryabets.first_pet.entity.CaptchaResponseDto;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.exceptions.UserExist;
import com.eronryabets.first_pet.service.UserService;
import com.eronryabets.first_pet.utility.UtilsBindingErrors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;


    private final RestTemplate restTemplate;
    private final UserService userService;

    public RegistrationController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(
            @RequestParam("g-recaptcha-response")String captchaResponse,
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model
    ) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        model.addAttribute("username", user.getUsername())
                .addAttribute("surname",user.getSurname())
                .addAttribute("name",user.getName());

        if(bindingResult.hasErrors() || !response.isSuccess() ){
            Map<String, String> errors = UtilsBindingErrors.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        try {
            userService.addUser(user);
        } catch (UserExist e){
            model.addAttribute("errorMessage", "This username " + user.getUsername() +
                    " already exist! Please input another username");
            return "registration";
        }

        return "redirect:/login";
    }

}