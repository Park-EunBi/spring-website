package com.eunbi.demo.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// posts 클래스로 DB 에 접근하게 해줄 인터페이스
// Dao 라고 불리는 DB Layer 접근자
// JPA 에서는 Repository 라고 부르며 인터페이스로 생성
// 인터페이스 생성 후 <Entity 클래스, PK 타입> 을 상속하면 기본적인 CRUD 자동 생성
// 주의!! Entity 클래스와 Repository 는 같은 위치로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // springDataJpa 에서 제공하지 않는 메소드는 추가 가능
    // 기본 메소드만으로 해결 가능하나 가독성이 더 좋다
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
