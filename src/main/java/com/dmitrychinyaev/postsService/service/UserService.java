package com.dmitrychinyaev.postsService.service;

import com.dmitrychinyaev.postsService.domain.Role;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        Optional<String> emailToActivate = Optional.ofNullable(user.getEmail());

        if (emailToActivate.isPresent()) {
            String textToSend = String.format(
                    "Hello and welcome, %s! \n" +
                            "Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSenderService.sendEmail(user.getEmail(), "Activation code", textToSend);
        }
        return true;
    }

    public boolean activateUser(String code) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByActivationCode(code));
        if (optionalUser.isEmpty()) {
            return false;
        } else {
            User user = optionalUser.get();
            user.setActivationCode(null);
            userRepository.save(user);
            return true;
        }
    }
}