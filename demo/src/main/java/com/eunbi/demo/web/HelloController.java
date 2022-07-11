package com.eunbi.demo.web;

import com.eunbi.demo.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 간단한 API 생성
// 컨트롤러를 JSON을 반환하는 컨트롤러로 생성
@RestController
public class HelloController {
    // GET 요청을 받을 수있는 API 생성
    @GetMapping("/hello")
    public String hello(){
        // http://localhost:8080/hello 에 출력되는 문자
        return "hello";
    }

    // dto 를 사용할 수 있도록 코드 변경
    @GetMapping("/hello/dto")
    //"name" 으로 넘긴 parameter 는 method parameter String name 으로 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
