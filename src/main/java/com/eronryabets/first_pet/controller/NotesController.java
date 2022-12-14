package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Notes;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.NotesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public String notes(
            @RequestParam(required = false, defaultValue = "") String filter,
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("user", user);
        Iterable<Notes> notes = notesService.findByAuthor(user);

        if (filter != null && !filter.isEmpty()) {
            notes = notesService.findByTag(filter);
        } else {
            notes = notesService.findByAuthor(user);
        }
        model.addAttribute("notes", notes)
        .addAttribute("filter", filter);

        return "notesList";
    }

    @PostMapping
    public String addNotes(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model
    ) {

        notesService.add(user, text, tag);
        Iterable<Notes> notes = notesService.findAll();
        model.put("notes", notes);

        return "redirect:/notes";
    }

    @RequestMapping(value = "/delete/{notes}",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNotes(@PathVariable Notes notes) {
        notesService.deleteNotes(notes);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{notes}")
    public String notesEdit(@PathVariable Notes notes) {
        return "notesEdit";
    }

    @PostMapping("/edit/{notes}")
    public String notesSave(
            @AuthenticationPrincipal User user,
            @PathVariable Notes notes,
            @RequestParam String text,
            @RequestParam String tag
    ) {
        notesService.add(user, text, tag);
        return "redirect:/notes";
    }


}
