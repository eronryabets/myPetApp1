package com.eronryabets.first_pet;

import com.eronryabets.first_pet.entity.Finance;
import com.eronryabets.first_pet.entity.Message;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.entity.Wallet;
import com.eronryabets.first_pet.repository.FinanceRepository;
import com.eronryabets.first_pet.repository.WalletRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application-test.properties")
@WithUserDetails(value = "admin")
@RunWith(SpringRunner.class)
public class FinanceTest {

    @Autowired
    FinanceRepository financeRepository;

    @Autowired
    WalletRepository walletRepository;


    @Test
    public void findAll(){
        Iterable<Finance> financeList = financeRepository.findAll();
        financeList.forEach(System.out::println);

    }

    @Test
    public void findFinanceByWallet(){
        Wallet walletFromDB = walletRepository.findById(2L).get();
        System.out.println(walletFromDB);

        List<Finance> financeList = financeRepository.findByWallet(walletFromDB);
        financeList.forEach(System.out::println);
    }

    @Test
    public void usTest(){
        User user = new User();
        Message mes1 = new Message("text","tag",user);
        System.out.println(mes1);
        System.out.println(mes1.getAuthor().getUsername());
    }

}
