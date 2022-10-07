package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.repository.UserRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application-test.properties")
@RunWith(SpringRunner.class)
@WithUserDetails(value = "admin")
class WalletServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WalletRepository walletRepository;

    @BeforeAll
    public static void globalSetupStart(){
        System.out.println("Start test");
    }

    @AfterAll
    public static void globalEndTest(){
        System.out.println("End test");
    }

    @Test
    void walletAdminSaveShouldThrowException() {
        User user = userRepository.findByUsername("Admin");
        List<Wallet> allByUser = walletRepository.findAllByUser(user);
        Wallet wallet = allByUser.get(0);

        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () -> {
            wallet.setUser(userRepository.findById(999L).get());
            walletRepository.save(wallet);
        });

       Assertions.assertEquals("No value present", thrown.getMessage());
    }

    @Test
    void walletAdminSave() {
        User user = userRepository.findByUsername("Admin");
        List<Wallet> allByUser = walletRepository.findAllByUser(user);
        Wallet wallet = allByUser.get(0);
        wallet.setUser(user);
        wallet.setBalance(777);
        walletRepository.save(wallet);

        Wallet walletFromDB = walletRepository.findById(2L).get();
        Assertions.assertEquals(777,walletFromDB.getBalance());

    }

    @Test
    void walletEdit() {
        User user = userRepository.findByUsername("Admin");
        List<Wallet> allByUser = walletRepository.findAllByUser(user);
        Wallet wallet = allByUser.get(0);
        System.out.println(wallet.getBalance());
        wallet.setUser(user);
        wallet.setBalance(777);
        walletRepository.save(wallet);
        System.out.println(wallet.getBalance());
    }
}