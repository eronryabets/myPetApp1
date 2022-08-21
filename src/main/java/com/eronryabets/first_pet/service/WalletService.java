package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.CurrencyWallet;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void walletSave(Wallet wallet, String walletName, double balance, double cashAdd){
        wallet.setWalletName(walletName);
        wallet.setBalance(balance+cashAdd);
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
