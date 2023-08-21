package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Optional<String> optionalFilter = Optional.ofNullable(filter);
        Optional<String> optionalTagFilter = Optional.ofNullable(tagFilter);
        if (optionalTagFilter.isPresent()) {
            messages = findByTag(tagFilter);
        }
        if (optionalFilter.isPresent()) {
            messages = findByUsername(filter);
        }
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user, @Valid Message message,
            BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);

        if(bindingResult.hasErrors()){
            Map<String,String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField() + "Error",
                            FieldError::getDefaultMessage
                    ));
            model.mergeAttributes(errors);
            model.addAttribute("message",message);
        } else {
            Optional<MultipartFile> fileReceived = Optional.ofNullable(file);
            if (fileReceived.isPresent()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + filename));
                message.setFilename(filename);
            }
            model.addAttribute("message",null);
            messageService.saveMessage(message);
        }
        Iterable<Message> messages = messageService.allMessagesList();
        model.addAttribute("messages", messages);
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
