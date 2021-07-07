package com.excmul.domain.user.dto;

import com.excmul.domain.common.baseentity.DateEntity;
import com.excmul.domain.user.Gender;
import com.excmul.domain.user.Role;
import com.excmul.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class UserDto { // DateEntity 및 BaseEntity에 대해 어떻게 진행할지 Fix
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private Gender gender;
    private Role role;

    public UserDto(Long id, String email, String password, String name, String nickName, Gender gender, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.gender = gender;
        this.role = role;
    }

    public User user() {
        return User.builder()
                .id(id)
                .email(email)
                .password(passwordEncoder(password))
                .name(name)
                .nickName(nickName)
                .gender(gender) // 작업 진행중
                .role(role) // 작업 진행중
                .build();
    }

    private String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
