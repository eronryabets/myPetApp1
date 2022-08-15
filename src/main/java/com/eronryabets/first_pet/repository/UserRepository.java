package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findBySurname (String username);

}
