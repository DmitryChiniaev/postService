package com.dmitrychinyaev.postsService.service;

import com.dmitrychinyaev.postsService.domain.User;
import com.dmitrychinyaev.postsService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }
}