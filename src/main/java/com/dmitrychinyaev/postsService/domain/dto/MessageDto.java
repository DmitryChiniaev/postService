package com.dmitrychinyaev.postsService.domain.dto;

import com.dmitrychinyaev.postsService.domain.Message;
import com.dmitrychinyaev.postsService.domain.User;
import lombok.Getter;

@Getter
public class MessageDto {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }
}
