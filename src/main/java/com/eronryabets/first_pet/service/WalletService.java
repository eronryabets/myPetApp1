package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.*;
import com.eronryabets.first_pet.repository.FinanceRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            financeRepository.save(new Finance(wallet,cashAdd,WalletOperation.INCOME));
        }else {
            financeRepository.save(new Finance(wallet,cashAdd,WalletOperation.SPENDING));
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
            walletRepository.save(anotherWallet);
        }

    }



    public void walletDelete(Wallet wallet){
        walletRepository.delete(wallet);
    }

}
