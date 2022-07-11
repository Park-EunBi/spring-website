package com.eunbi.demo.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// spring data JPA 테스트
@ExtendWith(MockitoExtension.class)
// 별다른 설정이 없다면 H2 db 실행
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;


    // 단위 테스트가 끝날 때마다 수행되는 메소드
    @AfterEach
    // 테스트 후 데이터를 지우는 역할
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // posts 테이블에 insert, update 쿼리 실행
        // pk 가 겹치면 update, 아니면 insert 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sample@gamil.com")
                .build());

        //when
        // posts 테이블에 있는 모든 데이터 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
