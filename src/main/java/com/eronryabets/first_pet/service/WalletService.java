package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.*;
import com.eronryabets.first_pet.repository.FinanceRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import com.eronryabets.first_pet.utility.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static com.eronryabets.first_pet.utility.MyDate.maxDayInMonth;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private FinanceRepository financeRepository;

    int currentYear = MyDate.getCurrentYear();

    public List<Wallet> findAll(){
        return walletRepository.findAll();
    }


    public List<Wallet> findAllByUser(User user){
        return  walletRepository.findAllByUser(user);
    }

    public Iterable<Finance> findAllFinance(){
        return financeRepository.findAll();
    }

    public List<Finance> findFinanceByWallet(Wallet wallet){
        return financeRepository.findByWallet(wallet);
    }

    public List<Finance> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate){
        return financeRepository.findByDateBetween(startDate,endDate);
    }

    public void addWallet(User user, String walletName, double balance,
                          String walletCurrency, String walletType){
        CurrencyWallet resultCurrency = CurrencyWallet.valueOf(walletCurrency);
        WalletType resultType = WalletType.valueOf(walletType);
        Wallet wallet = new Wallet(walletName,balance, resultCurrency,resultType,user);
        walletRepository.save(wallet);
    }

    public void walletSave(Wallet wallet, String walletName, double balance){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        walletRepository.save(wallet);
    }

    public void walletSave(Wallet wallet, String walletName, double balance, double cashAdd){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance+cashAdd);
        if(cashAdd > 0){
            financeRepository.save(new Finance(wallet,cashAdd,WalletOperation.INCOME, wallet.getBalance()));
        }else {
            financeRepository.save(new Finance(wallet,cashAdd,WalletOperation.SPENDING,wallet.getBalance()));
        }
        walletRepository.save(wallet);

    }

    public void walletUserCashTransfer(Wallet wallet,  double amount, int anotherWalletId){
        Optional<Wallet> anotherWalletOptional = walletRepository.findById((long) anotherWalletId);
        if(anotherWalletOptional.isPresent()){
            Wallet anotherWallet = anotherWalletOptional.get();
            wallet.setBalance(wallet.getBalance()-amount);
            anotherWallet.setBalance(anotherWallet.getBalance()+amount);

            walletRepository.save(wallet);
            financeRepository.save(new Finance(wallet,-amount,WalletOperation.SPENDING,wallet.getBalance()));
            walletRepository.save(anotherWallet);
            financeRepository.save(new Finance(anotherWallet,amount,WalletOperation.INCOME, anotherWallet.getBalance()));
        }

    }

    public void walletDelete(Wallet wallet){
        walletRepository.delete(wallet);
    }

    public List<Finance> findLastFiveFinance(Wallet wallet){
        List<Finance> financeAll = financeRepository.findByWallet(wallet);
        List<Finance> lastFiveOperations = new ArrayList<>();
        ListIterator<Finance> listIterator = financeAll.listIterator(financeAll.size());
        int count = 0;
        while (listIterator.hasPrevious() && count < 5){
            count++;
            lastFiveOperations.add(listIterator.previous());
        }
        return lastFiveOperations;
    }

    public List<Finance> findFinanceByWalletLastWeek(Wallet wallet){
        return financeRepository.findFinanceByWalletLastWeek(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentWeek(Wallet wallet){
        return financeRepository.findFinanceByWalletCurrentWeek(wallet.getId());
    }

    public List<Finance> findFinanceByWalletLastMonth(Wallet wallet){
        return financeRepository.findFinanceByWalletLastMonth(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentMonth(Wallet wallet){
        return financeRepository.findFinanceByWalletCurrentMonth(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentYear(Wallet wallet){
        return financeRepository.findFinanceByWalletCurrentYear(wallet.getId());
    }

    public List<Finance> firstQuarter(Wallet wallet) {
        LocalDateTime date1 =LocalDateTime.of(currentYear, 1,1,0,0,0);
        LocalDateTime date2 =LocalDateTime.of(currentYear, 3,maxDayInMonth(currentYear,3),0,0);
        return financeRepository.findByWalletAndDateBetween(wallet,date1,date2);
    }

    public List<Finance> secondQuarter(Wallet wallet) {
        LocalDateTime date1 =LocalDateTime.of(currentYear, 4,1,0,0,0);
        LocalDateTime date2 =LocalDateTime.of(currentYear, 6,maxDayInMonth(currentYear,6),0,0);
        return financeRepository.findByWalletAndDateBetween(wallet,date1,date2);
    }

    public List<Finance> thirdQuarter(Wallet wallet) {
        LocalDateTime date1 =LocalDateTime.of(currentYear, 7,1,0,0,0);
        LocalDateTime date2 =LocalDateTime.of(currentYear, 9,maxDayInMonth(currentYear,6),0,0);
        return financeRepository.findByWalletAndDateBetween(wallet,date1,date2);
    }

    public List<Finance> fourthQuarter(Wallet wallet) {
        LocalDateTime date1 =LocalDateTime.of(currentYear, 10,1,0,0,0);
        LocalDateTime date2 =LocalDateTime.of(currentYear, 12,maxDayInMonth(currentYear,6),0,0);
        return financeRepository.findByWalletAndDateBetween(wallet,date1,date2);
    }

    public List<Finance> queryTEST(Wallet wallet) {
        LocalDateTime date = LocalDateTime.of(2022, 3,1,0,0);
        return financeRepository.findFinanceTEST(wallet.getId(),date);
    }

    public List<Finance> findByWalletAndDateBetween(Wallet wallet,String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);
        LocalDateTime localDateTimeStart = localStartDate.atTime(0, 0, 0, 0);
        LocalDateTime localDateTimeEnd = localEndDate.atTime(0, 0, 0, 0);
        return financeRepository.findByWalletAndDateBetween(wallet,localDateTimeStart,localDateTimeEnd);
    }

    public ArrayList<Double> incomeSpendingValues(List<Finance> list){
        double income = 0;
        double spending = 0;
        for(Finance f : list){
            if(f.getAmountMoney() > 0){
                income += f.getAmountMoney();
            } else {
                spending -= f.getAmountMoney();
            }
        }
        ArrayList<Double> minMax = new ArrayList<>();
        minMax.add(income);
        minMax.add(spending);
        return minMax;
    }



















}
