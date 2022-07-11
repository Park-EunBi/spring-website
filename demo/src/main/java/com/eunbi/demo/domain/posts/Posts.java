package com.eunbi.demo.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity 클래스에는 절대로 setter 추가 x
//해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 명확하게 구분을 할 수 없기에
@Getter
// 기본 생성자 자동 추가
@NoArgsConstructor
// 테이블과 링크될 클래스임을 알림
@Entity
// Posts 클래스는 실제 DB 와 매칭될 클래스 - 보통 Entity class 라고 불림
// JPA 를 사용할 경우 실제 쿼리를 날리기 보단, Entity class 의 수정으로 작업
public class Posts {
    @Id // 해당 테이블의 PK
    // PK 생성규칙 - GenerationType.IDENTITY == auto_increase
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 컬럼, 기본값에 추가할 것이 있을 때만 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스에 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
