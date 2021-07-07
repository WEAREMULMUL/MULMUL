package com.excmul.domain.user.dto;

import com.excmul.domain.user.Gender;
import com.excmul.domain.user.Role;
import com.excmul.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private Gender gender;
    private Role role;

    public User user() {
        return User.builder()
                .id(id)
                .email(email)
                .password(passwordEncoder(password))
                .name(name)
                .nickName(nickName)
                .gender(Gender.MAN) // 작업 진행중
                .role(Role.GUEST)
                .build();
    }

    private String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
