package com.excmul.member.domain;

import com.excmul.auth.LoginMember;
import com.excmul.common.domain.AbstractEntity;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.*;
import com.excmul.member.dto.EditDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member extends AbstractEntity {
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
    }

    public void changePassword(PasswordVo password) {
        this.password = password;
    }

    public Mail newMail(Content mailContent) {
        return new Mail(email, mailContent);
    }

    public void editMemberInfo(EditDto editDto) {
        this.password = editDto.getPassword();
        this.nickname = editDto.getNickname();
        this.birth = editDto.getBirth();
        this.phoneNumber = editDto.getPhoneNumber();
    }
}