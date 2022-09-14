package com.eronryabets.first_pet;

import com.eronryabets.first_pet.entity.Role;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.UserRepository;
import com.eronryabets.first_pet.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserService userService = new UserService(userRepository,passwordEncoder);

    @Test
    public void addUser() {
        User user = new User();

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles())
                .matches(Collections.singleton(Role.USER)));
        Mockito.verify(userRepository, Mockito.times(1))
                .save(user);
    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("John");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0))
                .save(ArgumentMatchers.any(User.class));
    }






}
