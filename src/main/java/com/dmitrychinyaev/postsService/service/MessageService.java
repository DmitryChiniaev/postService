package com.dmitrychinyaev.postsService.service;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> allMessagesList(){
        return messageRepository.findAll();
    }

    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public List<Message> findByTag(String tag){
        return messageRepository.findByTag(tag);
    }

    public List<Message> findByUsername(String author){
        return messageRepository.findByAuthor_Username(author);
    }
}
