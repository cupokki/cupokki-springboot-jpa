package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.dto.MemberDetails;
import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.debug("Invalid login credentials");
                    return new UsernameNotFoundException("Invalid login credentials");
                });
//        log.debug(member.getEmail());
        return new MemberDetails(member);
    }
}
