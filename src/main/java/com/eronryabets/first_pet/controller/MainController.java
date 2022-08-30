package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.ExchangeRates;
import com.eronryabets.first_pet.service.MainService;
import com.eronryabets.first_pet.service.MessageService;
import com.eronryabets.first_pet.utility.GetPOJOExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("list",mainService.getExchangeRatesList());
        return "main";
    }

}

