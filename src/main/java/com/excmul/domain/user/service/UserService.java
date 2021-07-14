package com.excmul.domain.user.service;

import com.excmul.domain.user.vo.UserVo;
import com.excmul.domain.user.dto.UserDto;
import com.excmul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserDto userDto) {
        // UserVo user = userDto.user();
        // userRepository.save(user);
    }
}
