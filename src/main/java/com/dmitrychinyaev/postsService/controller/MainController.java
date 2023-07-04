package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageService messageService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,
                       String tagFilter, Model model) {
        Iterable<Message> messages = messageService.allMessagesList();
        if (tagFilter != null) {
            messages = messageService.findByTag(tagFilter);
        } else if (filter != null) {
            messages = messageService.findByUsername(filter);
        }
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageService.saveMessage(message);
        Iterable<Message> messages = messageService.allMessagesList();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filterByNickname")
    public String filterByNickname(@RequestParam String filter, Map<String, Object> model) {
        if (filter.isEmpty()) {
            model.put("messages", messageService.allMessagesList());
        } else {
            model.put("messages", messageService.findByUsername(filter));
        }
        return "main";
    }

    @PostMapping("filterByTag")
    public String filterByTag(@RequestParam String tagFilter, Map<String, Object> model) {
      if (tagFilter.isEmpty()) {
            model.put("messages", messageService.allMessagesList());
        } else {
            model.put("messages", messageService.findByTag(tagFilter));
        }
        return "main";
    }
}
