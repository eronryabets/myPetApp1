package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);

}
