package com.excmul.member.domain;

import com.excmul.auth.LoginMember;
import com.excmul.common.domain.DateEntity;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.*;
import lombok.*;

import javax.persistence.*;

@Getter(AccessLevel.PRIVATE)
@Builder
<<<<<<< HEAD
@NoArgsConstructor(access = AccessLevel.PROTECTED)
=======
>>>>>>> origin/jaewon
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
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

<<<<<<< HEAD
    public int id() {
        return id;
    }

    public EmailVo email() {
        return email;
    }

    public PasswordVo password() {
        return password;
    }

    public AuthVo auth() {
        return auth;
=======
    public LoginMember newLoginMember() {
        return LoginMember.builder()
                .id(id)
                .email(email)
                .auth(auth)
                .password(password)
                .build();
    }

    public PasswordChangeToken newChangePasswordToken() {
        return PasswordChangeToken.newInstance(this, this.password);
>>>>>>> origin/jaewon
    }

    public void changePassword(PasswordVo password) {
        this.password = password;
    }
<<<<<<< HEAD
=======

    public Mail newMail(Content mailContent) {
        return new Mail(email, mailContent);
    }
>>>>>>> origin/jaewon
}