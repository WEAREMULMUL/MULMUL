package com.excmul.member.domain;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.dto.SocialAttributes;
import com.excmul.common.domain.AbstractEntity;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.*;
import com.excmul.member.dto.EditDto;
import com.excmul.member.dto.SocialMemberInformation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    private String socialUserKey;

    @Embedded
    private NameVo name;

    @Embedded
    private NicknameVo nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_GENDER")
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
    @Column(name = "MEMBER_SOCIAL_TYPE", nullable = false)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE", nullable = false)
    private RoleVo role;

    public static Member ofSocial(SocialAttributes socialMember) {
        return Member.builder()
                .name(socialMember.name())
                .email(socialMember.email())
                .socialUserKey(socialMember.userKey())
                .socialType(socialMember.socialType())
                .role(RoleVo.USER)
                .build();
    }

    public boolean isSocial() {
        return socialType != SocialType.BASIC;
    }

    public AuthPrincipal toAuthPrincipal() {
        return AuthPrincipal.builder()
                .id(id)
                .email(email)
                .password(password)
                .role(role)
                .socialType(socialType)
                .build();
    }

    public boolean isNotCompletedSingUp() {
        return Objects.isNull(nickname) ||
                Objects.isNull(birth) ||
                Objects.isNull(phoneNumber);
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

    public void updateSocialMemberInfo(SocialMemberInformation socialMemberInformation) {
        this.gender = socialMemberInformation.getGender();
        this.nickname = socialMemberInformation.getNickname();
        this.phoneNumber = socialMemberInformation.getPhoneNumber();
        this.birth = socialMemberInformation.getBirth();
        this.termLocation = socialMemberInformation.isTermLocation();
        this.termPrivacy = true;
        this.termService = true;
    }
}