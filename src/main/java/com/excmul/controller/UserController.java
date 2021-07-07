package com.excmul.controller;

import com.excmul.domain.user.dto.UserDto;
import com.excmul.domain.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserController {

    private final UserService userService;

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signUser")
    public String createUser(@Valid UserDto userDto, BindingResult bindingResult) {
        /*
        if (bindingResult.hasErrors()) {
            // ErrorPage를 아직 설정하지 못해서
            return "error";
        }
         */
        userService.createUser(userDto);

        return "redirect:/";
    }

}
