package com.eronryabets.first_pet.repository;

import com.eronryabets.first_pet.entity.Message;
import com.eronryabets.first_pet.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    Page<Message> findAll(Pageable pageable);

    Page<Message> findByTag(String tag, Pageable pageable);

    List<Message> findByAuthor(User user);

}
