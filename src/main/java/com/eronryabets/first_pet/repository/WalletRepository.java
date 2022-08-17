package com.eronryabets.first_pet.repository;


import com.eronryabets.first_pet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {


}
