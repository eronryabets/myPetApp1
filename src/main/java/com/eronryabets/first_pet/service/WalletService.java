package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.CurrencyWallet;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> findAll(){
        return walletRepository.findAll();
    }

    public List<Wallet> findAllByUser(User user){
        return  walletRepository.findAllByUser(user);
    }

    public void addWallet(User user, String walletName, double balance, String walletCurrency){
        CurrencyWallet result = CurrencyWallet.valueOf(walletCurrency);
        Wallet wallet = new Wallet(walletName,balance, result,user);
        walletRepository.save(wallet);
    }

    public void walletSave(Wallet wallet, String walletName, double balance){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        walletRepository.save(wallet);
    }

    public void walletDelete(Wallet wallet){
        walletRepository.delete(wallet);
    }

}
