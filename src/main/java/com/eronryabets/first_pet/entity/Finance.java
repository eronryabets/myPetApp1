package com.eronryabets.first_pet.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="wallet_finance")
public class Finance {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "string_date")
    private String stringDate;

    @Column(name = "amount")
    private double amountMoney;

    @Column(name = "operation", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private WalletOperation operation;

    @Column(name = "balance")
    private double balance;

    public Finance() {
    }

    public Finance(Wallet wallet, double amountMoney, WalletOperation operation, double balance) {
        this.wallet = wallet;
        this.amountMoney = amountMoney;
        this.operation = operation;
        this.balance = balance;
        LocalDateTime timeNow = LocalDateTime.now();
        this.date = timeNow;
        this.stringDate = formatter(timeNow);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public WalletOperation getOperation() {
        return operation;
    }

    public void setOperation(WalletOperation operation) {
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static String formatter(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String time = date.format(formatter);
        return time;
    }
}
