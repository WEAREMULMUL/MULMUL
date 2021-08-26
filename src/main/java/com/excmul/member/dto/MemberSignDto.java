package com.excmul.member.dto;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
public class MemberSignDto {
    @NonNull
    private Email email;
    @Setter
    @NonNull
    private Password password;
    @NonNull
    private Name name;
    @NonNull
    private Nickname nickname;
    @NonNull
    private Gender gender;
    @NonNull
    private Birth birth;
    @NonNull
    private PhoneNumber phoneNumber;
    @NonNull
    private boolean termService;
    @NonNull
    private boolean termPrivacy;
    @NonNull
    private boolean termLocation;

    public Member toMember(PasswordEncoder passwordEncoder) {
        Password encodedPassword = password.encode(passwordEncoder);

        return Member.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .termService(termService)
                .termPrivacy(termPrivacy)
                .termLocation(termLocation)
                .socialType(SocialType.BASIC)
                .role(Role.USER)
                .build();
    }
}
