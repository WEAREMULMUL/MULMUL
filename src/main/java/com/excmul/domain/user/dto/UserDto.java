package com.excmul.domain.user.dto;

import com.excmul.domain.user.vo.Gender;
import com.excmul.domain.user.vo.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;
    private String name;
    private String nickName;
    private Gender gender;
    private Role role;

    //
    // jwt <=

    private String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
