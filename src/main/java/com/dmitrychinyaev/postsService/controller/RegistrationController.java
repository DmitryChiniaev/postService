package com.dmitrychinyaev.postsService.controller;

import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, Model model){
        User userRequired = userService.findByUsername(user.getUsername());
        if(user.equals(userRequired)){
            model.addAttribute("usernameError", "User already exists!");
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated) model.addAttribute("message", "User successfully activated");
        else model.addAttribute("message", "Activation code is not found!");
        return "login";
    }
}
