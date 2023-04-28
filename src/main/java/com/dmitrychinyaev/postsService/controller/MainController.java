package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text, @RequestParam String tag,
            Map<String, Object> model) {

        messageRepository.save(new Message(text, tag, user));

        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filterByTag")
    public String filterByTag(@RequestParam String filter, Map<String, Object> model) {
        if (filter.isEmpty()) {
            model.put("messages", messageRepository.findAll());
        } else {
            model.put("messages", messageRepository.findByTag(filter));
        }
        return "main";
    }

    @PostMapping("filterByNickname")
    public String filterByNickname(@RequestParam String filter, Map<String, Object> model) {
        if (filter.isEmpty()) {
            model.put("messages", messageRepository.findAll());
        } else {
            model.put("messages", messageRepository.findByAuthor_Username(filter));
        }
        return "main";
    }
}
