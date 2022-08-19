package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile/{user}/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @GetMapping
    public String walletList(@PathVariable User user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("wallets", walletService.findAll());
        return "wallets";
    }

    @PostMapping
    public String addWallet(
                          @PathVariable User user,
                          @RequestParam("walletName") String walletName,
                          @RequestParam("balance") double balance,
                          @RequestParam("walletCurrency") String walletCurrency
    ){

        walletService.addWallet(user, walletName, balance, walletCurrency);
        return "redirect:/profile/{user}/wallets";
    }

    @GetMapping("{wallet}/edit")
    public String walletEditForm(
            @PathVariable("user") User user,
            @PathVariable("wallet") Wallet wallet,
            Model model){
        model.addAttribute("user",user);
        model.addAttribute("wallet",wallet);
        return "walletEdit";
    }

    @PostMapping("{wallet}/edit")
    public String walletSave(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance
    ){

        walletService.walletSave(wallet, walletName, balance);
        return "redirect:/profile/{user}/wallets/{wallet}/edit";
    }

    @RequestMapping(value = "{wallet}/delete",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String walletDelete(@PathVariable("wallet") Wallet wallet){
        walletService.walletDelete(wallet);
        return "redirect:/profile/{user}/wallets";
    }




}

