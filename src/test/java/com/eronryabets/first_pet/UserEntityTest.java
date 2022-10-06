package com.eronryabets.first_pet;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@AutoConfigureTestEntityManager
@Transactional
@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application-test.properties")
@WithUserDetails(value = "admin")
@RunWith(SpringRunner.class)
public class UserEntityTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void addUser(){
        User user = User.builder()
                .name("Tester")
                .surname("Test")
                .username("SuperTester")
                .active(true)
                .roles(Collections.singleton(Role.USER))
                .password("123456789")
                .build();
        userRepository.save(user);
        User userFromDB = userRepository.findByUsername(user.getUsername());
        System.out.println(userFromDB);
        Assert.assertEquals("SuperTester",userFromDB.getUsername());
    }

    @Test
    public void deleteUser(){
        User userFromDB = userRepository.findByUsername("admin");
        System.out.println(userFromDB);

        userRepository.delete(userFromDB);
    }


}
