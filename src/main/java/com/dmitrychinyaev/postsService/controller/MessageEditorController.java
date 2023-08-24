package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MessageEditorController {
    private final MessageRepository messageRepository;
    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages = user.getMessages();
        model.addAttribute("userChannel",user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (message.getAuthor().equals(currentUser)) {
            if (!text.isEmpty()) message.setText(text);
            if (!tag.isEmpty()) message.setTag(tag);
            Optional<MultipartFile> fileReceived = Optional.ofNullable(file);
            if (fileReceived.isPresent()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + filename));
                message.setFilename(filename);
            }
            messageRepository.save(message);
        }
        return "redirect:/user-messages/" + user;
    }
}
