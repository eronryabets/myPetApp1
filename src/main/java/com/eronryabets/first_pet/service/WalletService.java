package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.*;
import com.eronryabets.first_pet.exceptions.IncorrectInput;
import com.eronryabets.first_pet.exceptions.UserNotFoundException;
import com.eronryabets.first_pet.exceptions.WalletNotFoundException;
import com.eronryabets.first_pet.repository.FinanceRepository;
import com.eronryabets.first_pet.repository.UserRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import com.eronryabets.first_pet.utility.MyDate;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import static com.eronryabets.first_pet.utility.MyDate.maxDayInMonth;

@Service
public class WalletService {


    private final WalletRepository walletRepository;
    private final FinanceRepository financeRepository;
    private final UserRepository userRepository;

    int currentYear = MyDate.getCurrentYear();

    public WalletService(WalletRepository walletRepository, FinanceRepository financeRepository,
                         UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.financeRepository = financeRepository;
        this.userRepository = userRepository;
    }

    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }


    public List<Wallet> findAllByUser(User user) {
        return walletRepository.findAllByUser(user);
    }

    public Iterable<Finance> findAllFinance() {
        return financeRepository.findAll();
    }

    public List<Finance> findFinanceByWallet(Wallet wallet) {
        return financeRepository.findByWallet(wallet);
    }

    public List<Finance> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return financeRepository.findByDateBetween(startDate, endDate);
    }

    public void addWallet(User user, String walletName, double balance,
                          String walletCurrency, String walletType) {
        CurrencyWallet resultCurrency = CurrencyWallet.valueOf(walletCurrency);
        WalletType resultType = WalletType.valueOf(walletType);
        Wallet wallet = Wallet.builder()
                .walletName(walletName)
                .balance(balance)
                .currency(resultCurrency)
                .type(resultType)
                .user(user)
                .build();
        walletRepository.save(wallet);
    }

    public void walletSave(Wallet wallet, String walletName, double balance) {
        if (balance > 0) {
            financeRepository.save(new Finance(wallet, balance, WalletOperation.INCOME, wallet.getBalance()));
        } else {
            financeRepository.save(new Finance(wallet, balance, WalletOperation.SPENDING, wallet.getBalance()));
        }
        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        walletRepository.save(wallet);
    }

    public void walletAdminSave(Wallet wallet, String walletName, double balance, Long userId) {

        Optional<User> userFromDB = userId == null
                ? Optional.empty()
                : userRepository.findById(userId);
        User user = userFromDB.orElseThrow(() -> new UserNotFoundException("User not found"));

        wallet.setWalletName(walletName);
        wallet.setBalance(balance);
        wallet.setUser(user);

        if (balance > 0) {
            financeRepository.save(new Finance(wallet, balance, WalletOperation.INCOME, wallet.getBalance()));
        } else {
            financeRepository.save(new Finance(wallet, balance, WalletOperation.SPENDING, wallet.getBalance()));
        }

        walletRepository.save(wallet);
    }

    public void deleteFieldUser(User user) {
        walletRepository.findAllByUser(user).forEach(wallet -> {
            wallet.setUser(null);
            walletRepository.save(wallet);
        });

    }

    public void walletUserSaveCashAdd(Wallet wallet, double cashAdd) {
        wallet.setBalance(wallet.getBalance() + cashAdd);
        if (cashAdd > 0) {
            financeRepository.save(new Finance(wallet, cashAdd, WalletOperation.INCOME, wallet.getBalance()));
        } else {
            financeRepository.save(new Finance(wallet, cashAdd, WalletOperation.SPENDING, wallet.getBalance()));
        }
        walletRepository.save(wallet);

    }

    public void walletUserCashTransfer(Wallet wallet, double amount, Long anotherWalletId) {
        Optional<Wallet> anotherWallet = anotherWalletId == null
                ? Optional.empty()
                : walletRepository.findById(anotherWalletId);
        Wallet walletFromDB = anotherWallet.orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() - amount);
        walletFromDB.setBalance(walletFromDB.getBalance() + amount);

        walletRepository.save(wallet);
        financeRepository.save(new Finance(wallet, -amount, WalletOperation.SPENDING, wallet.getBalance()));
        walletRepository.save(walletFromDB);
        financeRepository.save(new Finance(walletFromDB, amount, WalletOperation.INCOME, walletFromDB.getBalance()));
    }

    public void walletDelete(Wallet wallet) {
        financeRepository.findByWallet(wallet).forEach(financeRepository::delete);
        walletRepository.delete(wallet);
    }

    public List<Finance> findLastFiveFinance(Wallet wallet) {
        List<Finance> financeAll = financeRepository.findByWallet(wallet);
        List<Finance> lastFiveOperations = new ArrayList<>();
        ListIterator<Finance> listIterator = financeAll.listIterator(financeAll.size());
        int count = 0;
        while (listIterator.hasPrevious() && count < 5) {
            count++;
            lastFiveOperations.add(listIterator.previous());
        }
        return lastFiveOperations;
    }

    public List<Finance> findFinanceByWalletLastWeek(Wallet wallet) {
        return financeRepository.findFinanceByWalletLastWeek(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentWeek(Wallet wallet) {
        return financeRepository.findFinanceByWalletCurrentWeek(wallet.getId());
    }

    public List<Finance> findFinanceByWalletLastMonth(Wallet wallet) {
        return financeRepository.findFinanceByWalletLastMonth(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentMonth(Wallet wallet) {
        return financeRepository.findFinanceByWalletCurrentMonth(wallet.getId());
    }

    public List<Finance> findFinanceByWalletCurrentYear(Wallet wallet) {
        return financeRepository.findFinanceByWalletCurrentYear(wallet.getId());
    }

    public List<Finance> firstQuarter(Wallet wallet) {
        LocalDateTime date1 = LocalDateTime.of(currentYear, 1, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(currentYear, 3, maxDayInMonth(currentYear, 3), 0, 0);
        return financeRepository.findByWalletAndDateBetween(wallet, date1, date2);
    }

    public List<Finance> secondQuarter(Wallet wallet) {
        LocalDateTime date1 = LocalDateTime.of(currentYear, 4, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(currentYear, 6, maxDayInMonth(currentYear, 6), 0, 0);
        return financeRepository.findByWalletAndDateBetween(wallet, date1, date2);
    }

    public List<Finance> thirdQuarter(Wallet wallet) {
        LocalDateTime date1 = LocalDateTime.of(currentYear, 7, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(currentYear, 9, maxDayInMonth(currentYear, 6), 0, 0);
        return financeRepository.findByWalletAndDateBetween(wallet, date1, date2);
    }

    public List<Finance> fourthQuarter(Wallet wallet) {
        LocalDateTime date1 = LocalDateTime.of(currentYear, 10, 1, 0, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(currentYear, 12, maxDayInMonth(currentYear, 6), 0, 0);
        return financeRepository.findByWalletAndDateBetween(wallet, date1, date2);
    }

    public List<Finance> queryTEST(Wallet wallet) {
        LocalDateTime date = LocalDateTime.of(2022, 3, 1, 0, 0);
        return financeRepository.findFinanceTEST(wallet.getId(), date);
    }

    public List<Finance> findByWalletAndDateBetween(Wallet wallet, String startDate, String endDate) {
        LocalDateTime localDateTimeStart = myDateFormatter(startDate);
        LocalDateTime localDateTimeEnd = myDateFormatter(endDate);
        return financeRepository.findByWalletAndDateBetween(wallet, localDateTimeStart, localDateTimeEnd);
    }


    public ArrayList<Double> incomeSpendingValues(List<Finance> list) {
        double income = 0;
        double spending = 0;
        for (Finance f : list) {
            if (f.getAmountMoney() > 0) {
                income += f.getAmountMoney();
            } else {
                spending -= f.getAmountMoney();
            }
        }
        ArrayList<Double> result = new ArrayList<>();
        result.add(income);
        result.add(spending);
        return result;
    }

    public ArrayList<Double> walletDebitState(Wallet wallet, String startDate, String endDate) {
        LocalDateTime startLocalDateTime = myDateFormatter(startDate);
        LocalDateTime endLocalDateTime = myDateFormatter(endDate);
        Duration duration = Duration.between(startLocalDateTime, endLocalDateTime);
        ArrayList<Double> result = new ArrayList<>();
        double amountWithPercentage = 0;
        double percentage = 0;
        double increaseAmount = 0;
        if (wallet.getCurrency() == CurrencyWallet.UAH) {
            amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                    (duration.toDays() * 0.0275)) / 100);
            percentage = duration.toDays() * 0.0275;
            increaseAmount = ((wallet.getBalance() * (duration.toDays() * 0.0275)) / 100);
        } else if (wallet.getCurrency() == CurrencyWallet.PLN) {
            amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                    (duration.toDays() * 0.0219)) / 100);
            percentage = duration.toDays() * 0.0219;
            increaseAmount = ((wallet.getBalance() * (duration.toDays() * 0.0219)) / 100);
        } else {
            amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                    (duration.toDays() * 0.0137)) / 100);
            percentage = duration.toDays() * 0.0137;
            increaseAmount = ((wallet.getBalance() * (duration.toDays() * 0.0137)) / 100);
        }

        result.add(amountWithPercentage);
        result.add(percentage);
        result.add(increaseAmount);

        return result;
    }

    public ArrayList<Double> walletCreditState(Wallet wallet, String startDate, String endDate) {
        LocalDateTime startLocalDateTime = myDateFormatter(startDate);
        LocalDateTime endLocalDateTime = myDateFormatter(endDate);
        Duration duration = Duration.between(startLocalDateTime, endLocalDateTime);
        ArrayList<Double> result = new ArrayList<>();
        double amountWithPercentage = 0;
        double percentage = 0;
        double reductionAmount = 0;
        if (wallet.getBalance() < 0) {
            if (wallet.getCurrency() == CurrencyWallet.UAH) {
                amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                        (duration.toDays() * 0.0275)) / 100);
                percentage = duration.toDays() * 0.0275;
                reductionAmount = ((wallet.getBalance() * (duration.toDays() * 0.0275)) / 100);
            } else if (wallet.getCurrency() == CurrencyWallet.PLN) {
                amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                        (duration.toDays() * 0.0219)) / 100);
                percentage = duration.toDays() * 0.0219;
                reductionAmount = ((wallet.getBalance() * (duration.toDays() * 0.0219)) / 100);

            } else {
                amountWithPercentage = wallet.getBalance() + ((wallet.getBalance() *
                        (duration.toDays() * 0.0137)) / 100);
                percentage = duration.toDays() * 0.0137;
                reductionAmount = ((wallet.getBalance() * (duration.toDays() * 0.0137)) / 100);
            }
        }

        result.add(amountWithPercentage);
        result.add(percentage);
        result.add(reductionAmount);
        return result;
    }

    public LocalDateTime myDateFormatter(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime localDateTime = localDate.atTime(0, 0, 0, 0);
        return localDateTime;
    }

    public String myFileWriter(Wallet wallet, String startDate, String endDate, double income,
                               double spending, List<Finance> financeList, String fileFormat) {
        Path path = Paths.get("F:\\Work\\TestProjects\\first_pet\\financeReports\\");

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter d1 = DateTimeFormatter.ofPattern("y.MM.d-HH.mm.ss");
        String time = String.valueOf(ldt.format(d1));
        String fileName = startDate + "-" + endDate + "." + wallet.getWalletName()
                + "." + fileFormat;

        File file = new File(path + "\\" + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(
                    "Wallet name: " + wallet.getWalletName() + " (id:" + wallet.getId() + ") \n"
                            + "From date " + startDate + " to date " + endDate + ";\n" +
                            "Income : " + income + "; Spending : " + spending + "\n" +
                            "Time reports create : " + time + "\n\n");

            for (Finance f : financeList) {
                writer.write(String.valueOf(f));
                writer.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    //For Test Exceptions
    public void walletCashTransfer(Wallet wallet, double amount, Long anotherWalletId) {

        if (anotherWalletId == null) {
            throw new IncorrectInput("Please input another wallet id");
        }

        Optional<Wallet> anotherWallet = anotherWalletId == null
                ? Optional.empty()
                : walletRepository.findById(anotherWalletId);
        Wallet walletFromDB = anotherWallet.orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        if (amount <= 0.0) {
            throw new IncorrectInput("sum must be greater than 0");
        }

        wallet.setBalance(wallet.getBalance() - amount);
        walletFromDB.setBalance(walletFromDB.getBalance() + amount);

        walletRepository.save(wallet);
        financeRepository.save(new Finance(wallet, -amount, WalletOperation.SPENDING, wallet.getBalance()));
        walletRepository.save(walletFromDB);
        financeRepository.save(new Finance(walletFromDB, amount, WalletOperation.INCOME, walletFromDB.getBalance()));
    }


}
