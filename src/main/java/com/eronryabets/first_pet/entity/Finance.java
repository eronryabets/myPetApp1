package com.eronryabets.first_pet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="wallet_finance")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Finance implements Comparable<Finance> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    @ToString.Include(name = "WALLET")
    @NonNull
    private Wallet wallet;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @Column(name = "string_date")
    @ToString.Exclude
    private String stringDate = formatter(date);

    @Column(name = "amount")
    @NonNull
    private double amountMoney;

    @Column(name = "operation", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private WalletOperation operation;

    @Column(name = "balance")
    @NonNull
    private double balance;

    public static String formatter(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String time = date.format(formatter);
        return time;
    }

    @Override
    public int compareTo(Finance otherFinance){
        return Double.compare(this.amountMoney, otherFinance.amountMoney);
    }

}
