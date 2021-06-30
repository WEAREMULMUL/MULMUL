package com.excmul.domain.user.dto;

import com.excmul.domain.util.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 제자 마음대로 ㄱㄱ싱..

// config <-- 따로

// DTO <- 뷰로 전달하는 MODEL을 다룸
// Auth...

// Embedded 사용해보기~~1
// Setter 금지 금지~~
// 현재 클래스 VO
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;
    // email 검증 <- email 데이터를 Value 객체로 던져서 유효성 체크

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String picture;

    // dto <- view로 데이터 던지기
    // vo <- 데이터 다루기~
    // password -> JWT 토근 암호하 방식 적용???????????????

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String nickName, String email, String phoneNumber, String picture, Role role) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String nickName, String phoneNumber, String picture) {
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}