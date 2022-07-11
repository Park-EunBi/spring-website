package com.eunbi.demo.web.dto;

import com.eunbi.demo.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.parser.Entity;

@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
