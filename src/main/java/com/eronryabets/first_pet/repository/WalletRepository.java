package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<User, Long> {
}
