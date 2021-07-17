package com.excmul.domain.user.dto;

import com.excmul.domain.user.entity.UserEntity;
import com.excmul.domain.user.vo.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Email email;
    private Username username;
    private Password password;
    private Nickname nickname;
    private Gender gender;
    private Birth birth;
    private PhoneNumber phoneNumber;
    private Terms terms;

    // private Location location;
    public UserEntity user(UserDto userDto) {
        return UserEntity.builder()
                .email(email)
                .username(username)
                .password(password)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .auth(Auth.DEFAULT)
                .build();
    }


}
