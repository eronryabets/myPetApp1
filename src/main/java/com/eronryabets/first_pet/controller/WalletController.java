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
            @RequestParam("walletCurrency") String walletCurrency,
            @RequestParam("walletType") String walletType
    ){
        walletService.addWallet(user, walletName, balance, walletCurrency, walletType);
        return "redirect:/wallets/userWallets";
    }

    @GetMapping("/profile/{wallet}")
    public String walletUserEdit(
            @PathVariable("wallet") Wallet wallet,
            Model model){
        model.addAttribute("wallet",wallet);
        model.addAttribute("finance",walletService.findLastFiveFinance(wallet));
        return "walletUserEdit";
    }

    @PostMapping(value ="/profile/{wallet}")
    public String walletUserSave(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance
    ){

        walletService.walletSave(wallet, walletName, balance);
        return "redirect:/wallets/profile/{wallet}";
    }

    @PostMapping(value = "/profile/{wallet}/cashAdd")
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

    @GetMapping("/profile/{wallet}/financeDetails")
    public String financeDetails(@PathVariable("wallet") Wallet wallet,
                                 Model model){
        model.addAttribute("wallet",wallet);
        model.addAttribute("finance",walletService.findFinanceByWallet(wallet));

        model.addAttribute("lastWeek",walletService.findFinanceByWalletLastWeek(wallet));
        model.addAttribute("incomeLastWeek",walletService.minMaxValue(walletService.
                findFinanceByWalletLastWeek(wallet)).get(0));
        model.addAttribute("spendingLastWeek",walletService.minMaxValue(walletService.
                findFinanceByWalletLastWeek(wallet)).get(1));

        model.addAttribute("currentWeek",walletService.findFinanceByWalletCurrentWeek(wallet));
        model.addAttribute("incomeCurrentWeek",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentWeek(wallet)).get(0));
        model.addAttribute("spendingCurrentWeek",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentWeek(wallet)).get(1));

        model.addAttribute("lastMonth",walletService.findFinanceByWalletLastMonth(wallet));
        model.addAttribute("incomeLastMonth",walletService.minMaxValue(walletService.
                findFinanceByWalletLastMonth(wallet)).get(0));
        model.addAttribute("spendingLastMonthMonth",walletService.minMaxValue(walletService.
                findFinanceByWalletLastMonth(wallet)).get(1));

        model.addAttribute("currentMonth",walletService.findFinanceByWalletCurrentMonth(wallet));
        model.addAttribute("incomeCurrentMonth",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentMonth(wallet)).get(0));
        model.addAttribute("spendingCurrentMonth",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentMonth(wallet)).get(1));

        model.addAttribute("currentYear",walletService.findFinanceByWalletCurrentYear(wallet));
        model.addAttribute("incomeCurrentYear",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentYear(wallet)).get(0));
        model.addAttribute("spendingCurrentYear",walletService.minMaxValue(walletService.
                findFinanceByWalletCurrentYear(wallet)).get(1));

        model.addAttribute("firstQuarter",walletService.firstQuarter(wallet));
        model.addAttribute("incomeFirstQuarter",walletService.minMaxValue(walletService.
                firstQuarter(wallet)).get(0));
        model.addAttribute("spendingFirstQuarter",walletService.minMaxValue(walletService.
                firstQuarter(wallet)).get(1));


        model.addAttribute("secondQuarter",walletService.secondQuarter(wallet));
        model.addAttribute("incomeSecondQuarter",walletService.minMaxValue(walletService.
                secondQuarter(wallet)).get(0));
        model.addAttribute("spendingSecondQuarter",walletService.minMaxValue(walletService.
                secondQuarter(wallet)).get(1));

        model.addAttribute("thirdQuarter",walletService.thirdQuarter(wallet));
        model.addAttribute("incomeThirdQuarter",walletService.minMaxValue(walletService.
                thirdQuarter(wallet)).get(0));
        model.addAttribute("spendingThirdQuarter",walletService.minMaxValue(walletService.
                thirdQuarter(wallet)).get(1));


        model.addAttribute("fourthQuarter",walletService.fourthQuarter(wallet));
        model.addAttribute("incomeFourthQuarter",walletService.minMaxValue(walletService.
                fourthQuarter(wallet)).get(0));
        model.addAttribute("spendingFourthQuarter",walletService.minMaxValue(walletService.
                fourthQuarter(wallet)).get(1));

        //TEST
        model.addAttribute("queryTEST",walletService.queryTEST(wallet));

        return "financeDetails";
    }



}


