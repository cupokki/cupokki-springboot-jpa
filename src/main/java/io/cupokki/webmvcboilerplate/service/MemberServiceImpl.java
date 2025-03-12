package io.cupokki.webmvcboilerplate.service;

import io.cupokki.webmvcboilerplate.dto.MemberCreateDto;
import io.cupokki.webmvcboilerplate.dto.MemberDto;
import io.cupokki.webmvcboilerplate.dto.MemberLoginDto;
import io.cupokki.webmvcboilerplate.entity.Member;
import io.cupokki.webmvcboilerplate.repository.MemberRepository;
import io.cupokki.webmvcboilerplate.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberCreateDto memberCreateDto) throws Exception {

        if (memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new Exception("중복된 이메일");
        if (memberRepository.existsByUsername(memberCreateDto.getUsername()))
            throw new Exception("중복된 유저이름");
        if (!memberCreateDto.getPassword().equals(memberCreateDto.getConfirmPassword()))
            throw new Exception("비밀번호 확인 불일치");

        Member member = Member.builder()
                .email(memberCreateDto.getEmail())
                .username(memberCreateDto.getUsername())
                .memberPw(passwordEncoder.encode(memberCreateDto.getPassword()))
                .build();
        var saved= memberRepository.save(member);

        return;
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        return null;
    }

    @Override
    public Boolean delete(MemberDto memberDto) {

        return null;
    }

    @Override
    public MemberDto getById(Long memberSeq) {
        return MemberDto.builder()
                .build();
    }
}
