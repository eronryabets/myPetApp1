package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.Notes;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public Iterable<Notes> findAll(){
        return notesRepository.findAll();
    }

    public Iterable<Notes> findByTag(String filter){
        return notesRepository.findByTag(filter);
    }

    public void add(User user, String text, String tag)  {
        Notes notes = new Notes(text, tag, user);
        notesRepository.save(notes);

    }

    public void deleteNotes(Notes notes) {
        notesRepository.deleteById(notes.getId());
    }





}
