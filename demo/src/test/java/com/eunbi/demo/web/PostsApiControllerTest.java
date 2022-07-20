package com.eunbi.demo.web;

import com.eunbi.demo.domain.posts.Posts;
import com.eunbi.demo.domain.posts.PostsRepository;
import com.eunbi.demo.web.dto.PostsSaveRequestsDto;
import com.eunbi.demo.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

// api/v1/posts 로 보낸 body 값이 잘 들어가는지 테스트
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository; // JPA로 CRUD 할 수 있도록 만든 인터페이스

    // springBootTest 에서 MockMvc 사용하기 위해 추가
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    // 매번 테스트가 시작되기 전에 MockMvc 인스턴스를 생성
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    // 임의의 사용자 인증 추가
    @WithMockUser(roles="USER")
    public void Posts_등록된다() throws Exception{
        // given
        // builder 를 통해 값을 set 한다
        String title = "title";
        String content = "content";
        PostsSaveRequestsDto requestDto = PostsSaveRequestsDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        // url 을 설정
        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        // 응답 결과를 받음
        // 임의의 사용자 인증 추가로 인한 코드 변경
        // ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // 생성된 MockMvc 를 통해 API 테스트 
        // body 영역은 문자열로 표현하기 ㅜ이해 object mapper 를 통해 json 으로 변환 
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        // 응답 결과를 확인
        // 응답 코드, 입력한 값이 올바르게 들어갔는지 확인
        /* 임의의 사용자 인증 추가로 인한 코드 변경
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        */
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }


    @Test
    @WithMockUser(roles="USER")
    public void Posts_수정된다() throws Exception{
        // given
        // 저장된 데이터가 없기에 한번 저장해야 한다
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        // builder 로 값을 set
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        // put 메소드 사용
        // 임의의 사용자 인증 추가로 인한 코드 변경
        // ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
        //        requestEntity, Long.class);
        //
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());



        // then
        // 임의의 사용자 인증 추가로 인한 코드 변경
        // assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        // assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);


    }
}