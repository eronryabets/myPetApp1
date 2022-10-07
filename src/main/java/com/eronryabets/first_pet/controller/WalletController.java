package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Finance;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.exceptions.IncorrectInput;
import com.eronryabets.first_pet.exceptions.UserNotFoundException;
import com.eronryabets.first_pet.exceptions.WalletNotFoundException;
import com.eronryabets.first_pet.service.WalletService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String walletList(Model model,
                             @RequestParam(required = false, value = "errorMessage") String errorMessage) {
        model.addAttribute("wallets", walletService.findAll());
        model.addAttribute("errorMessage", errorMessage);
        return "walletsList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{wallet}")
    public String walletEditForm(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam(required = false, value = "errorMessage") String errorMessage,
            Model model) {
        model.addAttribute("wallet", wallet);
        model.addAttribute("errorMessage", errorMessage);
        return "walletEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String walletAdminSave(
            @RequestParam("walletId") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance,
            @RequestParam("user_id") Long userId,
            RedirectAttributes redirectAttributes
    ) {

        try {
            walletService.walletAdminSave(wallet, walletName, balance, userId);
        } catch (UserNotFoundException e) {
            redirectAttributes.addAttribute("errorMessage",
                    "Owner id " + userId + " is not exists!");
        }
        return "redirect:/wallets";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "{wallet}/delete",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    public String walletDelete(@PathVariable("wallet") Wallet wallet) {
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
    ) {
        walletService.addWallet(user, walletName, balance, walletCurrency, walletType);
        return "redirect:/wallets/userWallets";
    }

    @GetMapping("/profile/{wallet}")
    public String walletUserEdit(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam(required = false, value = "message") String message,
            Model model
    ) {
        model.addAttribute("wallet", wallet)
                .addAttribute("finance", walletService.findLastFiveFinance(wallet));
        model.addAttribute("message", message);
        return "walletUserEdit";
    }

    @PostMapping(value = "/profile/{wallet}")
    public String walletUserSave(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("walletName") String walletName,
            @RequestParam("balance") double balance
    ) {

        walletService.walletSave(wallet, walletName, balance);
        return "redirect:/wallets/profile/{wallet}";
    }

    @PostMapping(value = "/profile/{wallet}/cashAdd")
    public String walletUserSaveCashAdd(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("cashAdd") double cashAdd
    ) {

        walletService.walletUserSaveCashAdd(wallet, cashAdd);
        return "redirect:/wallets/profile/{wallet}";
    }


    @RequestMapping(value = "/profile/{wallet}/delete",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    public String walletUserDelete(@PathVariable("wallet") Wallet wallet) {
        walletService.walletDelete(wallet);
        return "redirect:/wallets/userWallets";
    }

    @PostMapping("/profile/{wallet}/transfer")
    public String walletUserCashTransfer(
            @PathVariable("wallet") Wallet wallet,
            @RequestParam("amount") double amount,
            @RequestParam("anotherWalletId") int anotherWalletId,
            RedirectAttributes redirectAttributes
    ) {
        if (!walletService.walletUserCashTransfer(wallet, amount, anotherWalletId)) {
            redirectAttributes.addAttribute("message", "Wallet id "
                    + anotherWalletId + " not exists!");
            return "redirect:/wallets/profile/{wallet}";
        }

        return "redirect:/wallets/profile/{wallet}";
    }

    @GetMapping("/profile/{wallet}/financeDetails")
    public String financeDetails(@PathVariable("wallet") Wallet wallet,
                                 Model model) {
        model.addAttribute("wallet", wallet)
                .addAttribute("finance", walletService.findFinanceByWallet(wallet));

        model.addAttribute("lastWeek", walletService.findFinanceByWalletLastWeek(wallet))
                .addAttribute("incomeLastWeek", walletService.incomeSpendingValues(walletService
                        .findFinanceByWalletLastWeek(wallet)).get(0))
                .addAttribute("spendingLastWeek", walletService.incomeSpendingValues(walletService
                        .findFinanceByWalletLastWeek(wallet)).get(1));

        model.addAttribute("currentWeek", walletService.findFinanceByWalletCurrentWeek(wallet))
                .addAttribute("incomeCurrentWeek", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentWeek(wallet)).get(0))
                .addAttribute("spendingCurrentWeek", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentWeek(wallet)).get(1));

        model.addAttribute("lastMonth", walletService.findFinanceByWalletLastMonth(wallet))
                .addAttribute("incomeLastMonth", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletLastMonth(wallet)).get(0))
                .addAttribute("spendingLastMonthMonth", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletLastMonth(wallet)).get(1));

        model.addAttribute("currentMonth", walletService.findFinanceByWalletCurrentMonth(wallet))
                .addAttribute("incomeCurrentMonth", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentMonth(wallet)).get(0))
                .addAttribute("spendingCurrentMonth", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentMonth(wallet)).get(1));

        model.addAttribute("currentYear", walletService.findFinanceByWalletCurrentYear(wallet))
                .addAttribute("incomeCurrentYear", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentYear(wallet)).get(0))
                .addAttribute("spendingCurrentYear", walletService
                        .incomeSpendingValues(walletService.findFinanceByWalletCurrentYear(wallet)).get(1));

        model.addAttribute("firstQuarter", walletService.firstQuarter(wallet))
                .addAttribute("incomeFirstQuarter", walletService
                        .incomeSpendingValues(walletService.firstQuarter(wallet)).get(0))
                .addAttribute("spendingFirstQuarter", walletService
                        .incomeSpendingValues(walletService.firstQuarter(wallet)).get(1));


        model.addAttribute("secondQuarter", walletService.secondQuarter(wallet))
                .addAttribute("incomeSecondQuarter", walletService
                        .incomeSpendingValues(walletService.secondQuarter(wallet)).get(0))
                .addAttribute("spendingSecondQuarter", walletService
                        .incomeSpendingValues(walletService.secondQuarter(wallet)).get(1));

        model.addAttribute("thirdQuarter", walletService.thirdQuarter(wallet))
                .addAttribute("incomeThirdQuarter", walletService
                        .incomeSpendingValues(walletService.thirdQuarter(wallet)).get(0))
                .addAttribute("spendingThirdQuarter", walletService
                        .incomeSpendingValues(walletService.thirdQuarter(wallet)).get(1));


        model.addAttribute("fourthQuarter", walletService.fourthQuarter(wallet))
                .addAttribute("incomeFourthQuarter", walletService
                        .incomeSpendingValues(walletService.fourthQuarter(wallet)).get(0))
                .addAttribute("spendingFourthQuarter", walletService
                        .incomeSpendingValues(walletService.fourthQuarter(wallet)).get(1));

        model.addAttribute("queryTEST", walletService.queryTEST(wallet));

        return "financeDetails";
    }

    @GetMapping("/profile/{wallet}/financeSelectDate")
    public String selectDateGet(@PathVariable("wallet") Wallet wallet,
                                Model model,
                                @RequestParam(required = false, value = "financeList") List<Finance> financeList,
                                @RequestParam(required = false, value = "startDate") String startDate,
                                @RequestParam(required = false, value = "endDate") String endDate,
                                @RequestParam(required = false, value = "fileName") String fileName,
                                @RequestParam(required = false, defaultValue = "0.0", value = "income") double income,
                                @RequestParam(required = false, defaultValue = "0.0", value = "spending") double spending

    ) {
        model.addAttribute("financeList", financeList)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate)
                .addAttribute("income", income)
                .addAttribute("spending", spending)
                .addAttribute("fileName", fileName);

        return "financeSelectDate";
    }


    @RequestMapping(path = "/profile/{wallet}/financeSelectDate", method = RequestMethod.POST)
    public String selectDatePost(@PathVariable("wallet") Wallet wallet,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("endDate") String endDate,
                                 @RequestParam("fileFormat") String fileFormat,
                                 RedirectAttributes redirectAttributes
    ) {
        List<Finance> financeList = walletService.findByWalletAndDateBetween(wallet, startDate, endDate);

        double income = walletService.incomeSpendingValues(walletService
                .findByWalletAndDateBetween(wallet, startDate, endDate)).get(0);
        double spending = walletService.incomeSpendingValues(walletService
                .findByWalletAndDateBetween(wallet, startDate, endDate)).get(1);

        String fileName = walletService.myFileWriter(wallet, startDate, endDate, income,
                spending, financeList, fileFormat);

        redirectAttributes.addAttribute("financeList", financeList)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate)
                .addAttribute("income", income)
                .addAttribute("spending", spending)
                .addAttribute("fileName", fileName);

        return "redirect:/wallets/profile/{wallet}/financeSelectDate";
    }


    @GetMapping("/profile/{wallet}/walletDebitStat")
    public String walletDebitState(@PathVariable("wallet") Wallet wallet,
                                   Model model,
                                   @RequestParam(required = false, defaultValue = "0.0",
                                           value = "amountWithPercentage") double amountWithPercentage,
                                   @RequestParam(required = false, defaultValue = "0.0",
                                           value = "percentage") double percentage,
                                   @RequestParam(required = false, defaultValue = "0.0",
                                           value = "increaseAmount") double increaseAmount,
                                   @RequestParam(required = false, value = "startDate") String startDate,
                                   @RequestParam(required = false, value = "endDate") String endDate
    ) {

        model.addAttribute("amountWithPercentage", amountWithPercentage)
                .addAttribute("percentage", percentage)
                .addAttribute("increaseAmount", increaseAmount)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate);

        return "walletDebitStat";
    }

    @RequestMapping(path = "/profile/{wallet}/walletDebitStat", method = RequestMethod.POST)
    public String walletDebitStatePost(@PathVariable("wallet") Wallet wallet,
                                       @RequestParam("startDate") String startDate,
                                       @RequestParam("endDate") String endDate,
                                       RedirectAttributes redirectAttributes
    ) {

        double amountWithPercentage = walletService.walletDebitState(wallet, startDate, endDate).get(0);
        double percentage = walletService.walletDebitState(wallet, startDate, endDate).get(1);
        double increaseAmount = walletService.walletDebitState(wallet, startDate, endDate).get(2);

        redirectAttributes.addAttribute("amountWithPercentage", amountWithPercentage)
                .addAttribute("percentage", percentage)
                .addAttribute("increaseAmount", increaseAmount)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate);

        return "redirect:/wallets/profile/{wallet}/walletDebitStat";
    }

    @GetMapping("/profile/{wallet}/walletCreditStat")
    public String walletCreditState(@PathVariable("wallet") Wallet wallet,
                                    Model model,
                                    @RequestParam(required = false, defaultValue = "0.0",
                                            value = "amountWithPercentage") double amountWithPercentage,
                                    @RequestParam(required = false, defaultValue = "0.0",
                                            value = "percentage") double percentage,
                                    @RequestParam(required = false, defaultValue = "0.0",
                                            value = "reductionAmount") double reductionAmount,
                                    @RequestParam(required = false, value = "startDate") String startDate,
                                    @RequestParam(required = false, value = "endDate") String endDate
    ) {

        model.addAttribute("amountWithPercentage", amountWithPercentage)
                .addAttribute("percentage", percentage)
                .addAttribute("reductionAmount", reductionAmount)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate);

        return "walletCreditStat";
    }

    @RequestMapping(path = "/profile/{wallet}/walletCreditStat", method = RequestMethod.POST)
    public String walletCreditStatePost(@PathVariable("wallet") Wallet wallet,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        RedirectAttributes redirectAttributes
    ) {

        double amountWithPercentage = walletService.walletCreditState(wallet, startDate, endDate).get(0);
        double percentage = walletService.walletCreditState(wallet, startDate, endDate).get(1);
        double reductionAmount = walletService.walletCreditState(wallet, startDate, endDate).get(2);

        redirectAttributes.addAttribute("amountWithPercentage", amountWithPercentage)
                .addAttribute("percentage", percentage)
                .addAttribute("reductionAmount", reductionAmount)
                .addAttribute("startDate", startDate)
                .addAttribute("endDate", endDate);

        return "redirect:/wallets/profile/{wallet}/walletCreditStat";
    }

    //For Test Exceptions
    @GetMapping("/profile/{wallet}/cashTransfer")
    public String walletCashTransferGet(@PathVariable("wallet") Wallet wallet,
                                        @RequestParam(required = false, value = "errorMessage")
                                                String errorMessage,
                                        Model model
    ) {
        model.addAttribute("errorMessage", errorMessage);

        return "cashTransfer";
    }

    //For Test Exceptions
    @RequestMapping(path = "/profile/{wallet}/cashTransfer", method = RequestMethod.POST)
    public String walletCashTransferPost(@PathVariable("wallet") Wallet wallet,
                                         @RequestParam(required = false,
                                                 defaultValue = "0.0", value = "amount") Double amount,
                                         @RequestParam(required = false, value = "anotherWalletId") Long anotherWalletId,
                                         RedirectAttributes redirectAttributes
    ) {

        try {
            walletService.walletCashTransfer(wallet, amount, anotherWalletId);
        } catch (WalletNotFoundException e) {
            redirectAttributes.addAttribute("errorMessage",
                    "Wallet id " + anotherWalletId + " is not exist!");
        } catch (IncorrectInput e) {
            redirectAttributes.addAttribute("errorMessage",
                    e.getMessage());
        }

        return "redirect:/wallets/profile/{wallet}/cashTransfer";
    }


}


