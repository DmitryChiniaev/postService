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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/test")
    public String testPage () {
        return "index";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,
                       String tagFilter, Model model) {
        Iterable<Message> messages = messageService.allMessagesList();
        if (tagFilter != null) {
            messages = findByTag(tagFilter);
        }
        if (filter != null) {
            messages = findByUsername(filter);
        }
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam("file") MultipartFile file,
            @RequestParam String tag, Map<String, Object> model) throws IOException {
        Message message = new Message(text, tag, user);

        if(!file.isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + filename));
            message.setFilename(filename);
        }

        messageService.saveMessage(message);
        Iterable<Message> messages = messageService.allMessagesList();
        model.put("messages", messages);
        return "main";
    }

    public List<Message> findByTag(String tag){
        if(tag.equals("")){
            return messageService.allMessagesList();
        }
        return messageService.findByTag(tag);
    }

    public List<Message> findByUsername(String username){
        if(username.equals("")){
            return messageService.allMessagesList();
        }
        return messageService.findByUsername(username);
    }
}
