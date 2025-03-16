package io.cupokki.webmvcboilerplate.security;

import io.cupokki.webmvcboilerplate.dto.MemberDetails;
import io.cupokki.webmvcboilerplate.service.MemberDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {
    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmailPasswordAuthenticationProvider(MemberDetailsService memberDetailsService, PasswordEncoder passwordEncoder) {
        this.memberDetailsService = memberDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("call");
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            UserDetails memberDetails = memberDetailsService.loadUserByUsername(email);
            if (!passwordEncoder.matches(password, memberDetails.getPassword())) {
                log.info("failed");
                throw new BadCredentialsException("Wrong password");
            }
            log.info("✅ 사용자 정보 조회 성공: {}", email);
            return new UsernamePasswordAuthenticationToken(memberDetails, password, memberDetails.getAuthorities());
        } catch (UsernameNotFoundException e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    // 해당 인증제공자가 특정 Authentication 타입을 지원하는지 반환
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
