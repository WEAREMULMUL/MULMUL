package com.excmul.auth.application;

import com.excmul.auth.dto.SocialAttributes;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.excmul.auth.exception.OAuth2Exception.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService,
        OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private static final String USERNAME_NOT_FOUND_MESSAGE = "해당 사용자를 찾을 수 없습니다.";
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Email email = new Email(userEmail);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE);
                });
        return member.toAuthPrincipal();
    }

    @Transactional
    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        SocialAttributes socialAttributes = newSocialAttributes(userRequest);
        Member member = findAndSaveSocialMember(socialAttributes);

        if (!member.isSocial()) {
            throw new OAuth2Exception(ErrorCode.NOT_FOUND_SOCIAL_TYPE);
        }

        return member.toAuthPrincipal();
    }

    private SocialAttributes newSocialAttributes(final OAuth2UserRequest userRequest) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.of(registrationId);
        OAuth2User oAuth2User = InnerLazyClass.DEFAULT_OAUTH2_USER_SERVICE.loadUser(userRequest);

        return socialType.toSocialAttributes(oAuth2User);
    }

    @Transactional
    public Member findAndSaveSocialMember(SocialAttributes socialAttributes) {
        return memberRepository.findByEmail(socialAttributes.email())
                .orElseGet(() ->
                        memberRepository.save(Member.ofSocial(socialAttributes))
                );
    }

    private static class InnerLazyClass {
        private static final DefaultOAuth2UserService DEFAULT_OAUTH2_USER_SERVICE = new DefaultOAuth2UserService();
    }
}