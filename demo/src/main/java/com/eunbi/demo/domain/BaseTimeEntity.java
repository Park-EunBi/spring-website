package com.eunbi.demo.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 모든 Entity 의 상위 클래스
// Entity 들의 createdDate, modifiedDate 자동 관리
@Getter
// Entity 클래스들이 BaseTimeEntity 을 상속할 때
// BaseTimeEntity 의 필드를 칼럼으로 추가
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing 기능 추가
    // JPA Auditing - 자동으로 시간을 맵핑하여 테이블에 추가해주는 기능
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // Entity 생성되어 저장될 때 시간 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // Entity 의 값을 변경할 때 시간 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
