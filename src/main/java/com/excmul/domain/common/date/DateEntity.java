package com.excmul.domain.common.date;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/* :: BaseTimeEntity [class]
 *
 * :: 기존 common.baseEntity에 있던 DateEntity를 대체해서 작성했습니다.
 *
 * 해당 클래스의 명칭은 기존 DateEntity와 같게 작성했습니다.
 * 기존 코드에서 해당 코드로 바꾼 이유는 아래와 같습니다.
 *
 * 1. 수정날짜와 생성날짜를 확인할 수 있다.
 * 2. 추가적인 수정이 용이하다.
 *
 * :: 패키지의 위치를 변경했습니다.
 *
 * common.baseentity -> common.date
 * 변경 이유는 아래와 같습니다.
 *
 * 1. baseentity를 패키지명으로 할시, 혼동을 야기할 수 있다고 생각했습니다.
 * 2. common 패키지 내부에서 쓰임을 확실하게 알 수 있다고 생각했습니다.
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}