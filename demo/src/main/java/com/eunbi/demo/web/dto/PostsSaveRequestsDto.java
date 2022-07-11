package com.eunbi.demo.web.dto;

import com.eunbi.demo.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 계층간 data 교환을 윈한 객체
// res, req 클래스
// Entity 클래스를 req, res 클래스로 사용해서는 안된다
@Getter
@NoArgsConstructor
public class PostsSaveRequestsDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestsDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
