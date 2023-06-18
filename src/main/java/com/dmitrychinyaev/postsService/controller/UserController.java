package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userService.findAllUsers());
        return "userList";
    }
}
