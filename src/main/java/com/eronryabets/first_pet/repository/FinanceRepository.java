package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.Finance;
import com.eronryabets.first_pet.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FinanceRepository extends CrudRepository<Finance, Integer> {
    List<Finance> findByWallet(Wallet wallet);

    List<Finance> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);



}
