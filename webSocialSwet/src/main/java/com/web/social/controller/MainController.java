package com.web.social.controller;

import com.web.social.model.Message;
import com.web.social.model.User;
import com.web.social.repo.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageRepo messageRepo;

    @GetMapping("/")
    public ModelAndView greeting(Map<String, Object> model) {
        return new ModelAndView("greeting");
    }

    @GetMapping("/main")
    public ModelAndView main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return new ModelAndView("main");
    }

    @PostMapping("/main")
    public ModelAndView add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {
        Message message = new Message(text, tag, user);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return new ModelAndView("main");
    }
}
