package com.eronryabets.first_pet.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users_wallet")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long id;

    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "balance")
    private double balance;


    @Column(name = "currency", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private CurrencyWallet currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User user;


    @Column(name = "type", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private WalletType type;

    public boolean isDebit(){
        return type.equals(WalletType.DEBIT);
    }

}
