package com.eronryabets.first_pet.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name="users_wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private CurrencyWallet currency;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

    public Wallet() {
    }

    public Wallet(double balance, CurrencyWallet currency) {
        this.balance = balance;
        this.currency = currency;
    }

    public Wallet(double balance, CurrencyWallet currency, User user) {
        this.balance = balance;
        this.currency = currency;
        this.user = user;
    }

    public Long getId() {
        return id;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CurrencyWallet getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyWallet currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

/*
    @ElementCollection(targetClass = CurrencyWallet.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "currency", joinColumns = @JoinColumn(name = "wallet_id"))
    @Enumerated(EnumType.STRING)
    private Set<CurrencyWallet> currency;
 */