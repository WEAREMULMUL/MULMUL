package com.excmul.auth.oauth.application;

import com.excmul.auth.oauth.dto.SocialMember;
import com.excmul.auth.oauth.dto.SocialType;
import com.excmul.auth.oauth.dto.AuthPrincipal;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
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

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService,
        OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;

    private final HttpSession httpSession;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        EmailVo email = new EmailVo(userEmail);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 ");
                });
        return newAuthPrincipal(member);
    }

    private AuthPrincipal newAuthPrincipal(Member member) {
        return new AuthPrincipal(member.newLoginMember());
    }

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        SocialMember socialMember = newSocialMember(userRequest);
        Member member = saveSocialMember(
                newSocialMember(userRequest)
        );
        AuthPrincipal userDetails = newAuthPrincipal(member);
        httpSession.setAttribute("user", userDetails);

        return socialMember;
    }

    private SocialMember newSocialMember(final OAuth2UserRequest userRequest) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.of(registrationId);
        OAuth2User oAuth2User = InnerLazyClass.DEFAULT_OAUTH2_USER_SERVICE.loadUser(userRequest);

        return socialType.toSocialMember(oAuth2User);
    }

    @Transactional
    public Member saveSocialMember(SocialMember socialMember) {
        Member member = memberRepository.findByEmail(socialMember.getEmail())
                .orElseGet(() -> memberRepository.save(socialMember.toMember()));
        member.updateSocialInfo(socialMember.getName());

        return member;
    }

    private static class InnerLazyClass {
        private static final DefaultOAuth2UserService DEFAULT_OAUTH2_USER_SERVICE = new DefaultOAuth2UserService();
    }
}