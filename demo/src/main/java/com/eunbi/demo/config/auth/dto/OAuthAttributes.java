package com.eunbi.demo.config.auth.dto;

import com.eunbi.demo.domain.user.Role;
import com.eunbi.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttirbuteKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttirbuteKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User 에서 반환하는 사용자 정보는 Map 이기에 값 하나하나를 반환해야 한다
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attirbutes){
        return ofGoogle(userNameAttributeName, attirbutes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // User Entity 생성
    // OAuthAttributes 에서 엔티티 생성하는 시점은 처음 가입할 때
    // 가입할 때의 기본 권한을 Guest 로 주기 위해 role 빌더 값에는 Role.GUEST 사용
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
