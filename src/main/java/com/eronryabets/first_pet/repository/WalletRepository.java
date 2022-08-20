package com.eronryabets.first_pet.repository;


import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAllByUser(User user);

}
