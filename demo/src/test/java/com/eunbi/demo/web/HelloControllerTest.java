package com.eunbi.demo.web;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.*;

// spring boot test 와 junit 사이의 연결자 역할
@ExtendWith(MockitoExtension.class)
// web에 집중할 수 있는 테스트 어노테이션
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    // spring이 관리하는 bean 주입
    @Autowired
    // 웹 API를 test 할 때 사용
    // MVC test의 시작점
    // 이 클래스로 http get, post 등 api test 가능
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        // /hello 주소로 http get 요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // http의 status 를 검증 - isOK() == 200 인지 확인
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }
}