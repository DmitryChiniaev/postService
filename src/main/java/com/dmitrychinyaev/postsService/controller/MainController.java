package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageService messageService;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageService.allMessagesList();

        if (filter.isEmpty()) {
            model.addAttribute("messages", messageService.allMessagesList());
        } else {
            model.addAttribute("messages", messageService.findByTag(filter));
        }
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) {
        if(file!=null){

        }
        messageService.saveMessage(new Message(text, tag, user));

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
}
