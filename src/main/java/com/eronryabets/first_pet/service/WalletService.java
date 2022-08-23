package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.*;
import com.eronryabets.first_pet.repository.FinanceRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private FinanceRepository financeRepository;

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


}
