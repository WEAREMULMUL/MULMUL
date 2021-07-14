package com.excmul.domain.user.entity;

import com.excmul.domain.common.Code;
import com.excmul.domain.common.baseentity.DateEntity;
import com.excmul.domain.user.vo.Email;
import com.excmul.domain.user.vo.Nickname;
import com.excmul.domain.user.vo.Terms;
import com.excmul.domain.user.vo.Username;
import lombok.*;

import javax.persistence.*;


/*
 * :: User [class]
 *
 * 1. Code :: 식별자 PK :: 고민중 [Proceeding]
 * 2. Email :: 이메일 :: wrjs@naver.com [Complete]
 * 3. UserName :: 사용자명 :: 김동건 [Complete]
 * 4. Password :: 비밀번호 :: SDAlkjh!!slk~
 * 5. Role :: 권한 :: 사용자, 관리자
 * 6. NickName :: 닉네임 :: 외쳐갓동건 [Complete]
 * 7. Gender :: 성별 :: 남성, 여성, 없음
 * 8. Birth :: 생년월일 :: 1997-09-08 (생년월일 API 이용)
 * 9. PhoneNumber :: 전화번호 :: 010-3182-9709
 * 10. Term :: 개인정보 약관 :: 개인정보 약관 [Proceeding]
 * 11. Location :: 위치 :: 카카오 맵 API ::
 * 12. Auth :: 인증방식 :: 카카오, 구글, 깃허브, 일반
 *
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
public class User extends DateEntity {

    @EmbeddedId
    private Code code;

    @Embedded
    private Email email;

    @Embedded
    private Username username;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Terms terms;


}