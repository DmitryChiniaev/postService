package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.Role;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller()
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        if(checkUser != null){
            model.put("message", "User already exists!");
            return "registration";
        }
        return "redirect:/login";
    }
}
