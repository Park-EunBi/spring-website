package com.eunbi.demo.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 권한 관리
public enum Role {
    // spring security 의 권한 코드는 항상 ROLE_ 이 앞에 있어야 한다
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}