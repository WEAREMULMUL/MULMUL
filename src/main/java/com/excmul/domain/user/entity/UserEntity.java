package com.excmul.domain.user.entity;

import com.excmul.domain.common.date.DateEntity;
import com.excmul.domain.user.vo.*;
import lombok.*;

import javax.persistence.*;


/*
 * :: UserEntity [class]
 *
 * 1. id :: 식별자 PK :: 1
 * 2. Email :: 이메일 :: wrjs@naver.com [Complete]
 * 3. UserName :: 사용자명 :: 김동건 [Complete]
 * 4. Password :: 비밀번호 :: SDAlkjh!!slk~ [Complete]
 * 5. Role :: 권한 :: 사용자, 관리자 [Complete]
 * 6. NickName :: 닉네임 :: 외쳐갓동건 [Complete]
 * 7. Gender :: 성별 :: 남성, 여성, 없음 [Complete]
 * 8. Birth :: 생년월일 :: 1997-09-08 (생년월일 API 이용)
 * 9. PhoneNumber :: 전화번호 :: 010-3182-9709 [Complete]
 * 10. Term :: 개인정보 약관 :: 개인정보 약관
 * 11. Location :: 위치 :: 카카오 맵 API ::
 * 12. Auth :: 인증방식 :: 카카오, 구글, 깃허브, 일반
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


}