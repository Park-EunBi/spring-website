package com.eunbi.demo.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// spring boot test 와 junit 사이의 연결자 역할
@ExtendWith(MockitoExtension.class)
// web에 집중할 수 있는 테스트 어노테이션
// 에러 해결 -> 스캔 대상에서 securityconfig 제거
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {
    // spring이 관리하는 bean 주입
    @Autowired
    // 웹 API를 test 할 때 사용
    // MVC test의 시작점
    // 이 클래스로 http get, post 등 api test 가능
    private MockMvc mvc;

    // 가짜로 인증된 사용자 생성
    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        // /hello 주소로 http get 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // http의 status 를 검증 - isOK() == 200 인지 확인
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }

    // 가짜로 인증된 사용자 생성
    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        // API test에서 사용될 요청 파라미터를 설정
                        // String 만 가능
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath : json 응답값을 필드별로 검증
                // $ 기준으로 필드명 명시
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}