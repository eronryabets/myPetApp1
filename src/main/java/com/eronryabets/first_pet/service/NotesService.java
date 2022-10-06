package com.eronryabets.first_pet.service;

import com.eronryabets.first_pet.entity.Notes;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotesService {

    @Value("${upload.path}")
    private String uploadPath;

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Iterable<Notes> findAll(){
        return notesRepository.findAll();
    }

    public Iterable<Notes> findByAuthor(User user){
        return notesRepository.findByAuthor(user);
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

    public void deleteNotesByUser(User user){
        notesRepository.findByAuthor(user).forEach(notesRepository::delete);
    }





}
