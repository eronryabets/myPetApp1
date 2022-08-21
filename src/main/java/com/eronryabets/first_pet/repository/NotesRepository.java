package com.eronryabets.first_pet.repository;


import com.eronryabets.first_pet.entity.Notes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotesRepository extends CrudRepository<Notes, Integer> {
    List<Notes> findByTag(String tag);
}
