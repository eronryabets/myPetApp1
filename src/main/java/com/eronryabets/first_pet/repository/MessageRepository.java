package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);

}
