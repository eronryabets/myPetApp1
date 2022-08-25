package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.Finance;
import com.eronryabets.first_pet.entity.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
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



}

/*
  @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND date >= date('=:startDate')" +
            "AND date < date('=:endDate')")
    List<Finance> findFinanceByWalletDateBetween(@Param("walletId") long walletId,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate")Date endDate);
=============================================================================
@Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND date >= date('2022-01-01')" +
            "AND date < date('2022-03-31')")
    List<Finance> findFinanceByWalletDateBetween(@Param("walletId") long walletId);
    ========================================================================

     @Query("FROM Finance WHERE wallet.id =:walletId " +
            "AND date >= date('2022-01-21 00:00:00.000000')" +
            "AND date < date('2022-03-31 00:00:00.000000')")
    List<Finance> findFinanceByWalletDateBetween(@Param("walletId") long walletId);


 */