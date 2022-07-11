package com.eunbi.demo.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 선언된 모든 필드에 get 메소드 생성
@Getter
// 선언된 모든 final 필드가 생성된 생성자를 추가
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
