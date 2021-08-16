package com.excmul.member.domain;

import com.excmul.common.domain.DateEntity;
import com.excmul.member.domain.vo.*;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member extends DateEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Embedded
    private EmailVo email;

    @Embedded
    private PasswordVo password;

    @Embedded
    private NameVo name;

    @Embedded
    private NicknameVo nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_GENDER", nullable = false)
    private GenderVo gender;

    @Embedded
    private BirthVo birth;

    @Embedded
    private PhoneNumberVo phoneNumber;

    @Column(name = "MEMBER_TERM_SERVICE", nullable = false)
    private boolean termService;

    @Column(name = "MEMBER_TERM_PRIVACY", nullable = false)
    private boolean termPrivacy;

    @Column(name = "MEMBER_TERM_LOCATION", nullable = false)
    private boolean termLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_AUTH", nullable = false)
    private AuthVo auth;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE", nullable = false)
    private RoleVo role;

}