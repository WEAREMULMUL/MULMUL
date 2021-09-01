package com.excmul.member.domain;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.dto.SocialAttributes;
import com.excmul.common.domain.AbstractEntity;
import com.excmul.follow.domain.Follow;
import com.excmul.mail.domain.Mail;
import com.excmul.mail.domain.vo.Content;
import com.excmul.member.domain.vo.*;
import com.excmul.member.dto.MemberInfoEditDto;
import com.excmul.member.dto.SocialMemberInformationDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "MEMBER")
public class Member extends AbstractEntity<Long> {
    @Embedded
    private Email email;

    @Embedded
    private Password password;

    private String socialUserKey;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_GENDER")
    private Gender gender;

    @Embedded
    private Birth birth;

    @Embedded
    private PhoneNumber phoneNumber;

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
    private Role role;

    @OneToMany(mappedBy = "fromMember", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Follow> fromFollow = new HashSet<>();

    @OneToMany(mappedBy = "toMember", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Follow> toFollow = new HashSet<>();

    public static Member ofSocial(SocialAttributes socialMember) {
        return Member.builder()
                .name(socialMember.name())
                .email(socialMember.email())
                .socialUserKey(socialMember.userKey())
                .socialType(socialMember.socialType())
                .role(Role.USER)
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

    public void changePassword(Password password) {
        this.password = password;
    }

    public Mail newMail(Content mailContent) {
        return new Mail(email, mailContent);
    }

    public void editMemberInfo(MemberInfoEditDto editDto) {
        this.password = editDto.getPassword();
        this.nickname = editDto.getNickname();
        this.birth = editDto.getBirth();
        this.phoneNumber = editDto.getPhoneNumber();
    }

    public void updateSocialMemberInfo(SocialMemberInformationDto socialMemberInformation) {
        this.gender = socialMemberInformation.getGender();
        this.nickname = socialMemberInformation.getNickname();
        this.phoneNumber = socialMemberInformation.getPhoneNumber();
        this.birth = socialMemberInformation.getBirth();
        this.termLocation = socialMemberInformation.isTermLocation();
        this.termPrivacy = true;
        this.termService = true;
    }

    public long id() {
        return id;
    }
}