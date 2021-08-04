package com.excmul.member.domain;

import com.excmul.common.domain.DateEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberEntity extends DateEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender", nullable = false)
    private Gender gender;

    @Embedded
    private Birth birth;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Terms terms;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_auth", nullable = false)
    private Auth auth;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

}