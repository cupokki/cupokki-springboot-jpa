package io.cupokki.webmvcboilerplate.dto;

import io.cupokki.webmvcboilerplate.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MemberDetails implements UserDetails {

    private final Member member;

    public MemberDetails(Member member) { // ✅ @Autowired 제거
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ✅ 기본 권한 설정 (필요하면 확장 가능)
        return Collections.emptyList(); // 또는 List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return member.getMemberPw();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // ✅ 계정 만료 여부 (true = 만료되지 않음)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // ✅ 계정 잠김 여부 (true = 잠기지 않음)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // ✅ 비밀번호 만료 여부 (true = 만료되지 않음)
    }

    @Override
    public boolean isEnabled() {
        return true; // ✅ 계정 활성화 여부 (true = 활성화됨)
    }

    public Member getMember() {
        return member; // ✅ 필요하면 Member 객체 직접 반환 가능
    }
}
