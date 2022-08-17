package com.eronryabets.first_pet.entity;


import javax.persistence.*;


@Entity
@Table(name="users_wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "balance")
    private double balance;


    @Column(name = "currency", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private CurrencyWallet currency;

    @ManyToOne(
            cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;

    public Wallet() {
    }

    public Wallet(String walletName, double balance, CurrencyWallet currency, User user) {
        this.walletName = walletName;
        this.balance = balance;
        this.currency = currency;
        this.user = user;
    }

    public Wallet(String walletName, double balance, CurrencyWallet currency) {
        this.walletName = walletName;
        this.balance = balance;
        this.currency = currency;
    }

    public Wallet(String walletName, double balance) {
        this.walletName = walletName;
        this.balance = balance;
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

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }


}
