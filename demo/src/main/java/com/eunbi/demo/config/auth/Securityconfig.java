package com.eunbi.demo.config.auth;

import com.eunbi.demo.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class Securityconfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // spring security 설정 활성화
                .csrf().disable().headers().frameOptions().disable()
                .and()
                // url 별 권한 관리 설정 옵션의 시작점
                // authorizeRequests 가 선언되어야만 antMatchers 옵션 사용 가능
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션
                // url, http 메소드 별로 관리 가능
                // "/" 등 지정된 url 들은 permitAll() 옵션을 통해 전체 열람 권한 줌
                // "/api/vi/**" 주소를 가진 API 는 USER 권한을 가진 사람만 열람 권한 줌
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값들 이외의 나머지 URL 을 나타냄
                // .authenticated() 권한으로 나머지 URL 들은 모두 인증된 사용자에게만 허용
                .anyRequest().authenticated()
                // 로그아웃 기능에 대한 여러 설정의 진입점
                // 로그아웃 성공 시 / 주소로 이동
                .and().logout().logoutSuccessUrl("/")
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                // 리소스 서버 (소셜 서비스들) 에서 사용자 정보를 가져온 상태에서
                // 추가로 진행하고자 하는 기능 명시
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
