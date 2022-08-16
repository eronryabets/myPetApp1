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
    public String walletList(@PathVariable User user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("wallets", walletRepository.findAll());
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

    @GetMapping("{wallet}")
    public String walletEditForm(
            @PathVariable("user") User user,
            @PathVariable("wallet") Wallet wallet,
            Model model){
        model.addAttribute("user",user);
        model.addAttribute("wallet",wallet);
        return "walletEdit";
    }

    @PostMapping("{wallet}")
    public String walletSave(
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance,
            @PathVariable("wallet") Wallet wallet
    ){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        walletRepository.save(wallet);
        return "redirect:/profile/{user}/wallets/{wallet}";
    }




}

/*
@PostMapping("{wallet}")
    public String walletSave(
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance,
            @RequestParam("walletId") Wallet wallet
//            @RequestParam("userId") User user
//            @PathVariable User user,
//            @PathVariable Wallet wallet

    ){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        walletRepository.save(wallet);
        return "redirect:/profile/{user}/wallets/{wallet}";

    }
 */
