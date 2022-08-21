package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Message;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String messages(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageService.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageService.findByTag(filter);
        } else {
            messages = messageService.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "messages";
    }

    @PostMapping
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        messageService.add(user, text, tag, file);
        Iterable<Message> messages = messageService.findAll();
        model.put("messages", messages);

        return "redirect:/messages";
    }

    @RequestMapping(value = "/delete/{message}",
            method={RequestMethod.DELETE, RequestMethod.GET})
    public String userMessageDelete(@PathVariable Message message){
        messageService.deleteMessage(message);
        return "redirect:/messages";
    }



}
