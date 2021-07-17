package com.excmul.domain.user.entity;

import com.excmul.domain.area.AreaEntity;
import com.excmul.domain.common.date.DateEntity;
import com.excmul.domain.user.vo.*;
import lombok.*;

import javax.persistence.*;


/*
 * :: UserEntity [class]
 *
 * 1. id :: 식별자 PK :: 1 [Complete]
 * 2. Email :: 이메일 :: wrjs@naver.com [Complete]
 * 3. UserName :: 사용자명 :: 김동건 [Complete]
 * 4. Password :: 비밀번호 :: SDAlkjh!!slk~ [Complete]
 * 5. Role :: 권한 :: 사용자, 관리자 [Proceeding]
 * 6. NickName :: 닉네임 :: 외쳐갓동건 [Complete]
 * 7. Gender :: 성별 :: 남성, 여성, 없음 [Proceeding]
 * 8. Birth :: 생년월일 :: 1997-09-08 [Complete]
 * 9. PhoneNumber :: 전화번호 :: 010-3182-9709 [Complete]
 * 10. Term :: 개인정보 약관 :: 개인정보 약관 [Proceeding]
 * 11. Location :: 위치 :: 카카오 맵 API [Proceeding]
 * 12. Auth :: 인증방식 :: 카카오, 구글, 깃허브, 일반 [Proceeding]
 *
 *
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class UserEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Embedded
    private Email email;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private Nickname nickname;

    // Default - Guest
    @Enumerated(EnumType.STRING)
    private Role role;

    // Default - Empty
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Birth birth;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Terms terms;

    @ManyToOne(fetch = FetchType.LAZY)
    private AreaEntity areaEntity;

    @Enumerated(EnumType.STRING)
    private Auth auth;

}