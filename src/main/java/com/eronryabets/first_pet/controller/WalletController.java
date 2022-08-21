package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String walletList(Model model) {
        model.addAttribute("wallets", walletService.findAll());
        return "walletsList";
    }

    @GetMapping("{wallet}")
    public String walletEditForm(
            @PathVariable("wallet") Wallet wallet,
            Model model){
        model.addAttribute("wallet",wallet);
        return "walletEdit";
    }


    @PostMapping
    public String walletSave(
            @RequestParam("walletId") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance
    ){

        walletService.walletSave(wallet, walletName, balance);
        return "redirect:/wallets";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{wallet}/delete",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String walletDelete(@PathVariable("wallet") Wallet wallet){
        walletService.walletDelete(wallet);
        return "redirect:/wallets";
    }

    @GetMapping("/userWallets")
    public String userWallets(Model model,
                              @AuthenticationPrincipal User user) {
        model.addAttribute("wallets", walletService.findAllByUser(user));
        return "userWallets";
    }

    @PostMapping("/userWallets")
    public String addWallet(
            @AuthenticationPrincipal User user,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance,
            @RequestParam("walletCurrency") String walletCurrency
    ){
        walletService.addWallet(user, walletName, balance, walletCurrency);
        return "redirect:/wallets/userWallets";
    }

    @GetMapping("/profile/{wallet}")
    public String walletUserEdit(
            @PathVariable("wallet") Wallet wallet,
            Model model){
        model.addAttribute("wallet",wallet);
        return "walletUserEdit";
    }

    @PostMapping("/profile/{wallet}")
    public String walletUserSave(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance,
            @RequestParam("cashAdd") double cashAdd
    ){

        walletService.walletSave(wallet, walletName, balance, cashAdd);
        return "redirect:/wallets/profile/{wallet}";
    }

    @RequestMapping(value = "/profile/{wallet}/delete",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String walletUserDelete(@PathVariable("wallet") Wallet wallet){
        walletService.walletDelete(wallet);
        return "redirect:/wallets/userWallets";
    }

    @PostMapping("/profile/{wallet}/transfer")
    public String walletUserCashTransfer(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("amount") double amount,
            @RequestParam("anotherWalletId") int anotherWalletId
    ){

        walletService.walletUserCashTransfer(wallet, amount, anotherWalletId);
        return "redirect:/wallets/profile/{wallet}";
    }


}


