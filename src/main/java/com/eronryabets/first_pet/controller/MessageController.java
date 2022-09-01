package com.eronryabets.first_pet.controller;

import com.eronryabets.first_pet.entity.Message;
import com.eronryabets.first_pet.entity.User;
import com.eronryabets.first_pet.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String messages(@RequestParam(required = false, defaultValue = "") String filter,
                           Model model,
                           @PageableDefault(sort = {"date"},
                                   direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Message> page = messageService.findAll(pageable);

        if (filter != null && !filter.isEmpty()) {
            page = messageService.findByTag(filter, pageable);
        } else {
            page = messageService.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/messages");
        model.addAttribute("filter", filter);

        return "messages";
    }

    @PostMapping
    public String addMessage(
            @AuthenticationPrincipal User user,
            Message message,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        messageService.addMessage(user, message, file);
        Iterable<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);

        return "redirect:/messages";
    }

    @RequestMapping(value = "/delete/{message}",
            method = {RequestMethod.DELETE, RequestMethod.GET})
    public String userMessageDelete(@PathVariable Message message) {
        messageService.deleteMessage(message);
        return "redirect:/messages";
    }


}
