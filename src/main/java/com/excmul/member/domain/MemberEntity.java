package com.excmul.member.domain;

import com.excmul.common.domain.DateEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends DateEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Embedded
    @Column(nullable = false)
    private Email email;

    @Embedded
    @Column(name = "member_username", nullable = false)
    private Name username;

    @Embedded
    @Column(name = "member_password", nullable = false)
    private Password password;

    @Embedded
    @Column(name = "member_nickname", nullable = false)
    private Nickname nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private Gender gender;

    @Embedded
    @Column(name = "member_birth", nullable = false)
    private Birth birth;

    @Embedded
    @Column(name = "member_phoneNumber", nullable = false)
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_auth", nullable = false)
    private Auth auth;

}