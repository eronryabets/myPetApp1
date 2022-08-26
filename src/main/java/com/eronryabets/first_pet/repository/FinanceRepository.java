package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.Finance;
import com.eronryabets.first_pet.entity.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FinanceRepository extends CrudRepository<Finance, Integer> {
    List<Finance> findByWallet(Wallet wallet);

    List<Finance> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND WEEK (date) = WEEK( current_date ) - 1" +
            "AND YEAR( date) = YEAR( current_date )")
    List<Finance> findFinanceByWalletLastWeek(@Param("walletId") long walletId);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND WEEK (date) = WEEK( current_date )" +
            "AND YEAR( date) = YEAR( current_date )")
    List<Finance> findFinanceByWalletCurrentWeek(@Param("walletId") long walletId);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND MONTH (date) = MONTH(CURRENT_DATE()) - 1" +
            "AND YEAR( date) = YEAR(CURRENT_DATE())")
    List<Finance> findFinanceByWalletLastMonth(@Param("walletId") long walletId);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND MONTH (date) = MONTH(CURRENT_DATE())" +
            "AND YEAR( date) = YEAR(CURRENT_DATE())")
    List<Finance> findFinanceByWalletCurrentMonth(@Param("walletId") long walletId);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND YEAR( date) = YEAR(CURRENT_DATE())")
    List<Finance> findFinanceByWalletCurrentYear(@Param("walletId") long walletId);

    List<Finance> findByWalletAndDateBetween(Wallet wallet,LocalDateTime startDate, LocalDateTime endDate);

    @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND date > :startDate")
    List<Finance> findFinanceTEST(@Param("walletId") long walletId,
                                          @Param("startDate") LocalDateTime startDate);

}
