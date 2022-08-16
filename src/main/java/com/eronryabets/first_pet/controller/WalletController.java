package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.CurrencyWallet;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile/{user}/wallets")
public class WalletController {
    @Autowired
    private WalletRepository walletRepository;

    @GetMapping
    public String userWallets(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        return "wallets";
    }

    @PostMapping
    public String addWallet(@RequestParam("walletName") String walletName,
                          @RequestParam("balance") int balance,
                          @RequestParam("walletCurrency") String walletCurrency,
                          @PathVariable User user
    ){
        CurrencyWallet result = CurrencyWallet.valueOf(walletCurrency);
        Wallet wallet = new Wallet(walletName,balance, result,user);
        walletRepository.save(wallet);
        return "redirect:/profile/{user}/wallets";
    }


}

