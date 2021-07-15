package com.excmul.domain.user.service;

// Service <- DTO 어떻게
// Vo <-> DTo

import com.excmul.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
}