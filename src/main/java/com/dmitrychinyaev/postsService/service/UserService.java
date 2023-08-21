package com.dmitrychinyaev.postsService.service;

import com.dmitrychinyaev.postsService.domain.Role;
import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoderUserService;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user, String username, Map<String,String> form){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(User user) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if(optionalUser.isPresent()){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoderUserService.encode(user.getPassword()));
        userRepository.save(user);
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
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

    public void updateProfile(User user, String password, String email) {
        Optional<String> emailReceived = Optional.ofNullable(email);
        Optional<String> userEmail = Optional.ofNullable(user.getEmail());
        Optional<String> passwordReceived = Optional.ofNullable(password);

        boolean isEmailChanged = (emailReceived.isPresent() && !emailReceived.equals(userEmail)) ||
                (userEmail.isPresent() && !userEmail.equals(emailReceived));

        if (isEmailChanged) {
            emailReceived.ifPresent(user::setEmail);
            passwordReceived.ifPresent(user::setPassword);
            user.setActivationCode(UUID.randomUUID().toString());
        }
        userRepository.save(user);
        sendMessage(user);
    }
}
