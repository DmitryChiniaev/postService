package com.dmitrychinyaev.postsService.repository;

import com.dmitrychinyaev.postsService.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByTag(String tag);
    List<Message> findByAuthor_Username(String author);
}
